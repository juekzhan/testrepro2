package com.xiangxuenet.basenetty.serializable.protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

public class ProtoBufServer {
   
	public void bind( int port) throws Exception{
		//配置服务端的NIO线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup,workerGroup)
			.channel(NioServerSocketChannel.class)
			//BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50
			.option(ChannelOption.SO_BACKLOG, 1024)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					//添加ProtobufVarint32FrameDecoder 以分离数据帧
					ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
					//添加  ProtobufDecoder 反序列化将字节解码为实体
					ch.pipeline().addLast(new ProtobufDecoder(PersonProto.Person.getDefaultInstance()));
					//添加业务代码
					ch.pipeline().addLast(new ProtoBufServerHandler());
				}
			});
			ChannelFuture f  = b.bind(port).sync();
			System.out.println("init start");
			f.channel().closeFuture().sync();
			//绑定端口，同步等待成功
		}finally {
			//优雅退出 释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception {
		new ProtoBufServer().bind(8085);
	}

}
