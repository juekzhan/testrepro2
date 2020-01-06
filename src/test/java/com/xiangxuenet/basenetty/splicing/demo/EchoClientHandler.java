package com.xiangxuenet.basenetty.splicing.demo;

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    
	private AtomicInteger counter = new  AtomicInteger(0);
	
	//客户端读取到网络数据后触发
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
     System.out.println("client Accept["+msg.toString(CharsetUtil.UTF_8)+"] and the counter  is "
    		 +  counter.incrementAndGet());		
	}
    
	//客户端被通知channel活跃后，做事
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf msg = null;
		String request = "juek,juek2,juek3,juek4,juek5,juek6" +  System.getProperty("line.separator");
		for(int i = 0; i< 10;i++) {   // 次发送 就粘到一起了 一起发送
			msg = Unpooled.buffer(request.length()); //转成byteBuf；
			msg.writeBytes(request.getBytes());
			ctx.writeAndFlush(msg);
		}
		
	}
    
	//发生异常时通知
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
    
	
	
}
