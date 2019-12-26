package com.xiangxuenet.basenetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


// 服务器端的入栈处理器
@ChannelHandler.Sharable ///*不加这个注解那么在增加到childHandler时就必须new出来*/
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    //这个是个无状态类，是线程安全的，但是加个统计  ，private int count; //就是线程 不安全的
	/**
	 * 去 buffer 中去读取数据 1次传完 （都会 调用一次）
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf)msg;
		System.out.println("Server accept:"+in.toString(CharsetUtil.UTF_8));
		ctx.write(in); //写到 缓冲区中
	}
   
	/**
	 * 在缓冲区中已经读完了的处理  （tcp的滑动窗口决定）
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) 
		.addListener(ChannelFutureListener.CLOSE);//写空（因为在前面已经写到缓冲区了）  ，强制刷到队端 //这个返回一个值 关闭链接
	}
    
	 //如果有异常  进行异常检查 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace(); //打印异常
        ctx.close();  //关闭当前的链接
    }
}
