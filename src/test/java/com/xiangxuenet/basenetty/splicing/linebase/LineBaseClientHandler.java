package com.xiangxuenet.basenetty.splicing.linebase;

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class LineBaseClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	
	private AtomicInteger counter = new AtomicInteger(0);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		System.out.println("client Accpet["+msg.toString(CharsetUtil.UTF_8)+"] and the counter is :"
				+counter.incrementAndGet());
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf msg = null;
        String request = "juek 1, juek2,juek3,juek4,juek5,juek6" + System.getProperty("line.separator");
        
        for(int i = 0;i < 10;i++) {
        	Thread.sleep(500);
        	System.out.println("即将发送数据.......");
            msg = Unpooled.buffer(request.length());
            msg.writeBytes(request.getBytes());
            ctx.writeAndFlush(msg);
            //ctx.writeAndFlush(Unpooled.copiedBuffer(request.getBytes()));   //等同于上面 的代码
        }
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	  cause.printStackTrace();
	  ctx.close();
	}
    
	
}
