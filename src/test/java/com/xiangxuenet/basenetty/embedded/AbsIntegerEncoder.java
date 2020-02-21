package com.xiangxuenet.basenetty.embedded;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

//入站处理器   ，来个编码
public class AbsIntegerEncoder extends MessageToMessageDecoder<ByteBuf> {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out)
			throws Exception {
        //检查是否有足够的字节来编码，int 为4个字节
		while(msg.readableBytes() >= 4) {
			//输入的 ByteBuf中读取下一个整数，并且计算其绝对值
        	int value = Math.abs(msg.readInt());
        	//将该整数写入到编码消息的list
        	out.add(value);
        }
	}

}
