package com.xiangxuenet.basenetty;

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandle extends SimpleChannelInboundHandler<ByteBuf> {
    
	private AtomicInteger counter = new AtomicInteger(0);
	
	/*客户端读到数据以后，就会执行*/
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		System.out.println("client  accept:" + msg.toString(CharsetUtil.UTF_8) 
		+ " AND COUNT" + counter.incrementAndGet());
	}
   
	//建立链接 之后
	 @Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//         //写完，然后刷新
//		 ctx.writeAndFlush(Unpooled.copiedBuffer(
//                "Hello Netty",CharsetUtil.UTF_8));
		 ByteBuf msg = null;
		 String request = "juek,tiantian,aiai,nini" + System.getProperty("line.separator");
		 for(int i = 0 ; i < 100 ; i++) {
			 msg = Unpooled.buffer(request.length());
			 msg.writeBytes(request.getBytes());
			 ctx.writeAndFlush(msg);
		 }
    }
	 
    //如果有异常  进行异常检查 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace(); //打印异常
        ctx.close();  //关闭当前的链接
    }
}
