package com.xiangxuenet.basenetty.splicing.demo;

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

//服务器端的入栈处理器
@ChannelHandler.Sharable ///*不加这个注解那么在增加到childHandler时就必须new出来*/
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
  private AtomicInteger counter = new AtomicInteger(0);
   
  //服务器读取到网络数据处理
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		String request = in.toString(CharsetUtil.UTF_8);
		System.out.println("Server  Accept["+request+"] and the counter is :" + counter.incrementAndGet());
		String rsp = "Hello"  + request + ".welcome to Netty World!" + System.getProperty("line.separator");
		ctx.writeAndFlush(Unpooled.copiedBuffer(rsp.getBytes()));
		//ctx.fireChannelRead();
        //ReferenceCountUtil.release(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
