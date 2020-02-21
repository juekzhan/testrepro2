package com.xiangxuenet.basenetty.splicing.fixed;

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


public class FixedLengthEchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
   
	private AtomicInteger counter = new AtomicInteger(0);
	
	private final String aa = "juek1,juek2,juek3,juek4,juek5,juek6,";
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
     		System.out.println("client Accept:["+msg.toString(CharsetUtil.UTF_8)+"] and the counter is:"+
	counter.incrementAndGet());
		
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf msg = null;
		for(int i = 0;i < 10 ; i++) {
			// 这边长度还要固定一下
			msg = Unpooled.buffer(FixedLengthEchoServer.RESPONSE.length());
			msg.writeBytes(aa.getBytes());
			ctx.writeAndFlush(msg);
		}
	}




	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
    
	
	
}
