package com.xiangxuenet.udpnetty.broadcast.client;

import com.xiangxuenet.udpnetty.broadcast.LogMsg;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogEventHandler extends SimpleChannelInboundHandler<LogMsg> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LogMsg msg) throws Exception {
		 //创建 StringBuilder，并且构建输出的字符串
        StringBuilder builder = new StringBuilder();
        builder.append(msg.getTime());
        builder.append(" [");
        builder.append(msg.getSource().toString());
        builder.append("] ：[");
        builder.append(msg.getMsgId());
        builder.append("] ：");
        builder.append(msg.getMsg());
        //打印 LogMsg 的数据
        System.out.println(builder.toString());
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
	

}
