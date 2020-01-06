package com.xiangxuenet.basenetty.splicing.linebase;

import java.net.InetSocketAddress;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

public class LineBaseEchoServer {
    private final int port;
	
	public LineBaseEchoServer(int port) {
		this.port = port;
	}
   
	public void start() throws InterruptedException{
		EventLoopGroup group = new  NioEventLoopGroup();
		try {
			final ServerBootstrap b = new ServerBootstrap();
			b.group(group).channel(NioServerSocketChannel.class)
			.localAddress(new InetSocketAddress(port)).childHandler(new ChannelInImp());
			
			ChannelFuture f = b.bind().sync();
			f.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully().sync();
		}
	}

	private static class ChannelInImp extends ChannelInitializer<Channel>{

		@Override
		protected void initChannel(Channel ch) throws Exception {
			ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
			ch.pipeline().addLast(new LineBaseEchoServerHandler());
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		new LineBaseEchoServer(9998).start();
	}

}
