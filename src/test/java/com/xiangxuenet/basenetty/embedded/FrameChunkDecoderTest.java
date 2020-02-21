package com.xiangxuenet.basenetty.embedded;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

public class FrameChunkDecoderTest {
   @Test
   public void frameChunkTest() {
	   //创建一个ByteBuf，并向它写入9个字节
	   ByteBuf buf = Unpooled.buffer();
	   for(int i = 0 ;i < 9;i++) {
		   buf.writeByte(i);
	   }
	   ByteBuf input = buf.duplicate();
	   
	   //创建一个
	   EmbeddedChannel channel =  new EmbeddedChannel(new FrameChunkDecoder(3));
	   
	   //向它写入2个字节，并 断言它们将会 产生一个新帧
	   assertTrue(channel.writeInbound(input.readBytes(2)));
	   try {
		   //写入一个4字节大小的帧
		   channel.writeInbound(input.readBytes(4));
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   //写入剩余的2字节，并断言将会产生一个有效的帧
	   assertTrue(channel.writeInbound(input.readBytes(3)));
	   //将该channel标记为已完成状态
	   assertTrue(channel.finish());
   }
}
