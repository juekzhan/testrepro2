package com.xiangxuenet.basenetty.serializable.protobuf;

import java.io.IOException;

import com.xiangxuenet.basenetty.serializable.protobuf.PersonProto.Person;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProtoBufServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//将上一个handler传入的数据强制转型
		PersonProto.Person  req  = (Person) msg;
		System.out.println("收到数据：name= "  + req.getName() + ",email = "+req.getEmail());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if(cause instanceof IOException) {
			System.out.println("远程客户端强迫关闭了一个现有 的链接 ");
		}
		ctx.close();
	}
   
}
