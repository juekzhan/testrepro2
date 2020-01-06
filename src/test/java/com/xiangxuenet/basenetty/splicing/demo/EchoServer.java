package com.xiangxuenet.basenetty.splicing.demo;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class EchoServer {
	private final int port;

	public EchoServer(int port) {
		this.port = port;
	}
	
	public void start() throws InterruptedException{
		final EchoServerHandler echoServerHandler = new EchoServerHandler();
		EventLoopGroup group = new NioEventLoopGroup(); //线程组
		try {
			ServerBootstrap b = new ServerBootstrap();
			 b.group(group)
			.channel(NioServerSocketChannel.class)
			.localAddress(new InetSocketAddress(port))
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(echoServerHandler);
				}
			});
			 ChannelFuture f = b.bind().sync();
			f.channel().closeFuture().sync();
		}finally {
			group.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new EchoServer(9999).start();
	}

}
