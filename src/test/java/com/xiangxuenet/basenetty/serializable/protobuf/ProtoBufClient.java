package com.xiangxuenet.basenetty.serializable.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ProtoBufClient {
      
	public void connect(int port, String host) throws Exception{
		//配置客户端NIO线程组
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
			//在有些网络通信的场景下，要求低延迟，这样就需要我们设置一些TCP的链接属性：
			//设置这样做好的好处就是禁用nagle算法
			//Nagle算法试图减少TCP包的数量和结构性开销, 将多个较小的包组合成较大的包进行发送.但这不是重点, 关键是这个算法受TCP延迟确认影响, 会导致相继两次向连接发送请求包,
			//读数据时会有一个最多达500毫秒的延时.
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					//用来添加报文长度的字段 
					ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
					//添加 ProtobufEncoder 进行序列化将实体类编码为字节
					ch.pipeline().addLast(new ProtobufEncoder());
					//添加自己的业务Handler
					ch.pipeline().addLast(new ProtoBufClientHandler());
				}
			});
			ChannelFuture f = b.connect(host,port).sync();
			f.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully().sync();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		new ProtoBufClient().connect(8085, "127.0.0.1");

	}

}
