package com.xiangxuenet.basenetty.serializable.msgpack;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

//序列化  编码
public class MsgPackEncode extends MessageToByteEncoder<Object>{

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		MessagePack messagePack  = new MessagePack();
		byte[] raw =  messagePack.write(msg);
		out.writeBytes(raw);
	}

}
