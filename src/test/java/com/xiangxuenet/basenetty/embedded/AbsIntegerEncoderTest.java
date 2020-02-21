package com.xiangxuenet.basenetty.embedded;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

//测试出站处理，可以通过修改AbsIntegerEncoderTest看运行结果
public class AbsIntegerEncoderTest {
 
	@Test
	public void testEncoder() {
		//(1) 创建一个Buffer ，并且写入9个负数
		ByteBuf buf = Unpooled.buffer();
		for(int i = 1;i < 10;i++) {
			buf.writeInt(i * -1);
		}
		System.out.println(buf.readableBytes() + "@#@#!");
		//(2)创建一个EmbeddedChannel，并且安装一个要 测试 的 AbsIntegerEncoder
		EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
		//(3)写入ByteBuf，并断言调用 readOutbound() 方法 将会产生数据
		assertTrue(channel.writeOutbound(buf));
		//(4)将该channel标记为已完成状态
		assertTrue(channel.finish());
		
		//read bytes
		//(5) 读取所产生的消息，并断言它们包含了对应的绝对值
		for(int i = 1; i < 10; i++) {
		   System.out.println(channel.readOutbound()+"xxxxxxx");
//			System.out.println(x);
//			assertEquals(i, x);
		}
		
		assertNull(channel.readOutbound());
	}
}
