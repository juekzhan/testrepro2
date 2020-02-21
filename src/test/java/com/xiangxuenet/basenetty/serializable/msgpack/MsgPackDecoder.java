package com.xiangxuenet.basenetty.serializable.msgpack;

import java.util.List;

import org.msgpack.MessagePack;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
//解码器
public class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf> {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out)
			throws Exception {
		int length =  msg.readableBytes();
		byte[] array = new byte[length];
		msg.getBytes(msg.readerIndex(), array,0,length);
		MessagePack messagePack  = new MessagePack();
		out.add(messagePack.read(array,User.class));
		//ReferenceCountUtil.release(msg);   因为MessageToMessageDecoder 中都有释放的代码了
	} 
 
}
