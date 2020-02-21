package com.xiangxuenet.basenetty.splicing.delimiterEchoServer;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class DelimiterEchoServer {
	public static final String DELIMITER_SYMBOL = "@~";
	
	private final  int port;
	
	 public DelimiterEchoServer(int port) {
		this.port = port;
	}

    public void start() throws InterruptedException{
    	EventLoopGroup group = new  NioEventLoopGroup();
    	try {
    		final ServerBootstrap b = new ServerBootstrap();
    		b.group(group).channel(NioServerSocketChannel.class)
    		.localAddress(new InetSocketAddress(port))
    		.childHandler(new ChannelInintIm());
    		
    		
    		ChannelFuture f = b.bind().sync();
    		f.channel().closeFuture().sync();
    	}finally {
    		group.shutdownGracefully().sync();
    	}
    }
    
    private static class ChannelInintIm extends ChannelInitializer<Channel>{

		@Override
		protected void initChannel(Channel ch) throws Exception {
			//分隔符号
			ByteBuf delimiter = Unpooled.copiedBuffer(DelimiterEchoServer.DELIMITER_SYMBOL.getBytes());
			ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
			ch.pipeline().addLast(new DelimiterEchoServerHandler());
		}
    }

	public static void main(String[] args) throws InterruptedException {
		new DelimiterEchoServer(9995).start();
	}
}
