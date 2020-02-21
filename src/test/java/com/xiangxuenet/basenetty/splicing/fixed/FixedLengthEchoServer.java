package com.xiangxuenet.basenetty.splicing.fixed;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

public class FixedLengthEchoServer {
	 public static final String RESPONSE = "Welcome to Netty!";
	
	private final int prot;

	public FixedLengthEchoServer(int prot) {
		this.prot = prot;
	}
	
   public void start() throws InterruptedException{
	   EventLoopGroup group = new NioEventLoopGroup();
	   try {
		   final ServerBootstrap b  = new ServerBootstrap();
		   b.group(group).channel(NioServerSocketChannel.class)
		   .localAddress(prot).childHandler(new ChannInImp());
		   
		   ChannelFuture f = b.bind().sync();
		   f.channel().closeFuture().sync();
	   }finally {
		   group.shutdownGracefully().sync();
	   }
   }
   
   private static class ChannInImp extends ChannelInitializer<Channel>{

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ch.pipeline().addLast(new FixedLengthFrameDecoder(FixedLengthEchoServer.RESPONSE.length()));
		ch.pipeline().addLast(new FixedLengthEchoServerHandler());
	}
	   
   }

	public static void main(String[] args) throws InterruptedException {
		new FixedLengthEchoServer(9996).start();
	}

}
