package com.xiangxuenet.udpnetty.broadcast.client;

import java.net.InetSocketAddress;

import com.xiangxuenet.udpnetty.broadcast.LogConst;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

//类说明，日志的接收端 (可以启动)
public class LogEventMonitor {
	private final EventLoopGroup group;

	private final Bootstrap bootstrap;

	public LogEventMonitor(InetSocketAddress address) {
		group = new NioEventLoopGroup();
		bootstrap = new Bootstrap();
		bootstrap.group(group)
		.channel(NioDatagramChannel.class)
		//设置为 广播模式
		.option(ChannelOption.SO_BROADCAST, true)
		//允许端口 复用
		.option(ChannelOption.SO_REUSEADDR, true)
		.localAddress(address)
		.handler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ch.pipeline().addLast(new LogEventDecoder());
				ch.pipeline().addLast(new LogEventHandler());
			}
		});
	}
    
	public Channel bind() {
		//绑定 Channel。注意，DatagramChannel 是无连接的
		return bootstrap.bind().syncUninterruptibly().channel();
	}
	
	public void stop() {
		group.shutdownGracefully();
	}
	
	public static void main(String[] args) throws Exception {
		LogEventMonitor monitor = new LogEventMonitor(
				new InetSocketAddress(LogConst.MONITOR_SIDE_PORT));
		try {
			Channel channel = monitor.bind();
			System.out.println("UdpAnwersid runing");
			channel.closeFuture().sync();
		}finally {
			monitor.stop();
		}
	}
}
