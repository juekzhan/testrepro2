package com.xiangxuenet.basenetty.splicing.linebase;

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class LineBaseEchoServerHandler extends ChannelInboundHandlerAdapter {
   
	private AtomicInteger counter = new AtomicInteger(0);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端:"+ctx.channel().remoteAddress() +"已经连接");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	  ByteBuf in = (ByteBuf)msg;
	  String request  = in.toString(CharsetUtil.UTF_8);
      System.out.println("Server Accpet:["+request+"]and the counter is" + counter.incrementAndGet());
      
      String resp  = "Hello,"+ request+".WelCome to Netty World" + System.getProperty("line.separator");
      ctx.writeAndFlush(Unpooled.copiedBuffer(resp.getBytes()));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().remoteAddress()+"即将关闭");
	}
}
