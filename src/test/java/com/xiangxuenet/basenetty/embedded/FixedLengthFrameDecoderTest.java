package com.xiangxuenet.basenetty.embedded;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

public class FixedLengthFrameDecoderTest {
  
  @Test
  public void FixedTest() {
	  //创建一个ByteBuf，并存储9字节
	  ByteBuf buf = Unpooled.buffer();
	  for(int i = 0 ; i < 9 ;i++) {
		  buf.writeByte(i);
	  }
	  //拷贝到 一个新的 数组中
	  ByteBuf input = buf.duplicate();
	  
	  //创建一个EmbeddedChannel  ，并添加 一个FixedLengthFrameDecoder
	  //其将以3个字节长度 帧被 测试 
	  EmbeddedChannel channel = new EmbeddedChannel(
			  new FixedLengthFrameDecoder(3));
	  
	  //返回false,因为没有一个 完整的可供读取的帧
	  assertFalse(channel.writeInbound(input.readBytes(1)));
	  //还是返回  false，因为没有一个完整的可供读取的帧
	  assertFalse(channel.writeInbound(input.readBytes(1)));
	 //还是返回  false，因为没有一个完整的可供读取的帧
	  assertTrue(channel.writeInbound(input.readBytes(1)));
	 //将剩余的数据写入，准备readInbound测试
      assertTrue(channel.writeInbound(input.readBytes(6)));
      
      //标记  Channel 为已完成状态
      assertTrue(channel.finish());
      
      //read message
      //读取所生成的消息 ，并且验证是否 3 帧（切片），其中每帧（切片）都为3个字节
      ByteBuf read = (ByteBuf) channel.readInbound();
      //和源进行对比  读取3个字节
      assertEquals(buf.readSlice(3), read);
      read.release();
      
      read = (ByteBuf) channel.readInbound();
      assertEquals(buf.readSlice(3), read);
      read.release();
      
      read = (ByteBuf) channel.readInbound();
      assertEquals(buf.readSlice(3), read);
      read.release();
      
      assertNull(channel.readInbound());
      buf.release();
  }
}
