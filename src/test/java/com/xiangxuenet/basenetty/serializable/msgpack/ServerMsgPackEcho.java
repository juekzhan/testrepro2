package com.xiangxuenet.basenetty.serializable.msgpack;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class ServerMsgPackEcho {
   
	public static final int PORT = 9995;
	
	public void start() throws  InterruptedException{
		EventLoopGroup group  = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
			.channel(NioServerSocketChannel.class)
			.localAddress(new InetSocketAddress(PORT))
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					//长度66535 ，开始0，结束 2，读取 从0 --2
				ch.pipeline().addLast("frameDecoder",new LengthFieldBasedFrameDecoder(
						65535, 0, 2,0,2));
					//反序列化 解码
				ch.pipeline().addLast("MsgPack-Decoder",new MsgPackDecoder());

				//将反序列化后的实体类交给业务
				ch.pipeline().addLast(new ServerMsgPackEchoHandler());
				}
			});
			ChannelFuture f = b.bind(PORT).sync();
			f.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) throws Exception {
        new ServerMsgPackEcho().start();
	}

}
