package com.xiangxuenet.basenetty.helloword;

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
	super();
	this.port = port;
}
 
public static void main(String[] args) throws Exception {
	EchoServer server = new EchoServer(9999);
	 System.out.println("服务器即将启动");
	server.start();
	 System.out.println("服务器关闭");
}

 private void start() throws Exception {
	 final EchoServerHandler echoServerHandler = new EchoServerHandler();
	 //线程组
	 EventLoopGroup group = new NioEventLoopGroup();
	 
	 try {
		 ServerBootstrap b = new ServerBootstrap();
		 b.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
		 /*服务端每接收到一个连接请求，就会新启一个socket通信，也就是channel，
         所以下面这段代码的作用就是为这个子channel增加handle*/
		 .childHandler(new ChannelInitializer<SocketChannel>() {
			 /*添加到该子channel的pipeline的尾部*/ //形成一个 链表 
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(echoServerHandler);
			}
		});
		 ChannelFuture f = b.bind().sync();/*异步绑定到服务器，sync()会阻塞直到完成*/
		 f.channel().closeFuture().sync();/*阻塞直到服务器的channel关闭*/
	 }finally {
		 group.shutdownGracefully().sync();/*优雅关闭线程组*/
	 }
 }
 
}
