package com.xiangxuenet.basenetty.splicing.fixed;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

public class FixedLengthEchoClient {
	
	 public static final String RESPONSE = "Welcome to Netty!";
	
	private final int prot;

	private final String host;

	public FixedLengthEchoClient(int prot, String host) {
		this.prot = prot;
		this.host = host;
	}
    
	public void start() throws InterruptedException{
		EventLoopGroup group = new NioEventLoopGroup();
		
	    try {
	    	final Bootstrap b = new Bootstrap();
	    	b.group(group).channel(NioSocketChannel.class)
	    	.remoteAddress(new InetSocketAddress(host, prot))
	    	.handler(new ChannelInitImpl());
	    	
	    	ChannelFuture f = b.connect().sync();
	        f.channel().closeFuture().sync();
	    }finally {
	    	group.shutdownGracefully().sync();
	    }
	}
	
	private static class ChannelInitImpl extends ChannelInitializer<Channel>{

		@Override
		protected void initChannel(Channel ch) throws Exception {
			//加上对应的固定长度进行拆包，粘包的问题
			ch.pipeline().addLast(new FixedLengthFrameDecoder(FixedLengthEchoServer.RESPONSE.length()));
			ch.pipeline().addLast(new FixedLengthEchoClientHandler());
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		new FixedLengthEchoClient(9996,"127.0.0.1").start();
	}

}
