package com.xiangxuenet.basenetty.splicing.demo;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {
	private final int prot;
	private final String host;

	public EchoClient(int prot, String host) {
		this.prot = prot;
		this.host = host;
	}
    
	public void start() throws InterruptedException{
		EventLoopGroup group = new NioEventLoopGroup(); //线程组 （反应堆模式）
		try {
			final Bootstrap b = new Bootstrap(); //客户端的启动器
			b.group(group)   //将线程组传入
			.channel(NioSocketChannel.class) //指定nio模式通信
			.remoteAddress(new InetSocketAddress(host,prot)) //指定服务器 的端口于ip
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new EchoClientHandler());
				}
			});  //绑定处理器组
			ChannelFuture f = b.connect().sync(); // 线程同步返回一个值
			f.channel().closeFuture().sync();    //然后这个值拿到通道同步关闭
		}finally {
			group.shutdownGracefully().sync();  //关闭线程组
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new EchoClient(9999, "127.0.0.1").start();
	}
}
