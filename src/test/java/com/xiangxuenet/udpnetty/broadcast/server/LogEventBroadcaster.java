package com.xiangxuenet.udpnetty.broadcast.server;

import java.net.InetSocketAddress;

import com.xiangxuenet.udpnetty.broadcast.LogConst;
import com.xiangxuenet.udpnetty.broadcast.LogMsg;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

//广播类
public class LogEventBroadcaster {
   private final  EventLoopGroup  group;
   
   private final Bootstrap bootstrap;
   
   public LogEventBroadcaster(InetSocketAddress  remoteAddress) {
      group = new  NioEventLoopGroup();
      bootstrap = new Bootstrap();
      
      bootstrap.group(group).channel(NioDatagramChannel.class)
      //设置端口为广播模式
      .option(ChannelOption.SO_BROADCAST, true)
      .handler(new LogEventEncoder(remoteAddress));
   }
   
   public void run() throws Exception{
	   //绑定channel
	   Channel  ch =  bootstrap.bind(0).sync().channel();
	   long count = 0;
	   //启动主处理循环 ，模拟日志 发送
	   for(;;) {
		   ch.writeAndFlush(new LogMsg(null, LogConst.getLogInfo(),++count));
		   try {
			   Thread.sleep(2000);
		   }catch(InterruptedException e) {
			   Thread.interrupted();
			   break;
		   }
	   }
   }
   
   public void  stop() {
	   group.shutdownGracefully();
   }
   
   public static void main(String[] args) throws Exception {
	//启动并创建一个 新的实例  广播指定的网络内，用子网掩码
	   LogEventBroadcaster broadcaster = new  LogEventBroadcaster(
			   new InetSocketAddress("255.255.255.255",LogConst.MONITOR_SIDE_PORT));
	   try {
		   broadcaster.run();
	   }finally {
		   broadcaster.stop();
	   }
}
}
