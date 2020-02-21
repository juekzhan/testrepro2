package com.xiangxuenet.basenetty.serializable.msgpack;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.LineBasedFrameDecoder;

public class ClientMsgPackEcho {
    private final String host;
    
    public ClientMsgPackEcho(String host) {
    	this.host = host;
    }
	
    public void start() throws InterruptedException{
    	EventLoopGroup group = new NioEventLoopGroup();
    	try {
    		final  Bootstrap b = new Bootstrap();
    		b.group(group).channel(NioSocketChannel.class)
    		.remoteAddress(new InetSocketAddress(host,ServerMsgPackEcho.PORT))
    		.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					//设置报文的长度，避免粘包半包  // 在 对应的包上加上2 个字节的 标志字节有 多长
					ch.pipeline().addLast("frameEncode", new LengthFieldPrepender(2));
					//对发送数据的序列化
					ch.pipeline().addLast("MsaPack-Encoder",new MsgPackEncode());
					
					ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
					
					ch.pipeline().addLast(new ClientMsgPackEchoHandler(5));
				}
			});
    		ChannelFuture f = b.connect().sync();
    		System.out.println("已经链接到服务器");
    		f.channel().closeFuture().sync();
    	}finally {
    		group.shutdownGracefully().sync();
    	}
    }
    
    
	public static void main(String[] args) throws InterruptedException {
		new ClientMsgPackEcho("127.0.0.1").start();
	}

}
