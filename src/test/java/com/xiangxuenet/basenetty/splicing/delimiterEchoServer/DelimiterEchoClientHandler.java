package com.xiangxuenet.basenetty.splicing.delimiterEchoServer;

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class DelimiterEchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
      
	private AtomicInteger counter = new AtomicInteger(0);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		System.out.println("client Accept:["+msg.toString(CharsetUtil.UTF_8)+"] "
				+ "the nums:"+counter.incrementAndGet());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String request = "juek1,juek2,juek3,juek4"+ System.getProperty("line.separator");
		for(int i = 0 ;i < 10 ;i++) {
			ByteBuf msg = Unpooled.buffer(request.length());
			msg.writeBytes(request.getBytes());
			ctx.writeAndFlush(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
