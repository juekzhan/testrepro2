package com.xiangxuenet.basenetty.splicing.delimiterEchoServer;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class DelimiterEchoClient {
     private final int port;
     
     private final String host;
     
	public DelimiterEchoClient(int prot, String host) {
		this.port = prot;
		this.host = host;
	}

    public void start() throws InterruptedException{
    	EventLoopGroup group = new NioEventLoopGroup();
    	try {
    		final Bootstrap b = new Bootstrap();
    		b.group(group).channel(NioSocketChannel.class)
    		.remoteAddress(new InetSocketAddress(host, port)).
    		handler(new ChannelIm());
    		
    		ChannelFuture f = b.connect().sync();
    		f.channel().closeFuture().sync();
    	}finally {
			group.shutdownGracefully().sync();
		}
    }

   public static class ChannelIm extends ChannelInitializer<Channel>{

	@Override
	protected void initChannel(Channel ch) throws Exception {
        ByteBuf delimiter = Unpooled.copiedBuffer(DelimiterEchoServer.DELIMITER_SYMBOL.getBytes());
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
        ch.pipeline().addLast(new DelimiterEchoClientHandler());
	}
	   
   }

	public static void main(String[] args) throws InterruptedException {
		new DelimiterEchoClient(9995, "127.0.0.1").start();
	}

}
