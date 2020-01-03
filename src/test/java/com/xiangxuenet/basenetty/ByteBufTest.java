package com.xiangxuenet.basenetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.util.ReferenceCountUtil;

public class ByteBufTest {

	public static void main(String[] args) {
		// 1.分配管理 堆内存，直接内存 复合缓冲区
		//直接分配一个非池化技术的缓冲区 （true 为直接内存，false 为堆内存）
		ByteBufAllocator allocatorFactory = new UnpooledByteBufAllocator(true);  
		//分配一个池化的缓冲区
		ByteBufAllocator allocatorPool = new PooledByteBufAllocator();
       
		ByteBuf buf = allocatorFactory.buffer(1024);
		
		//2,访问
		buf.readerIndex(); //手动指引索引
		buf.readLong(); //手动移动8位
		//buf.indexOf(3,5,'C');
		
		//3,可写字节，可读 字节，可丢弃字节
		buf.discardReadBytes();//把可读字节往前移动，（带了内存无为的拷贝）
		buf.clear(); //把 readIndex 和 writeIndex 置为0
		
		//4，索引管理 ，查找
		buf.markReaderIndex(); //
		buf.resetReaderIndex() ;// 复位到一定的
		//buf.forEachByte('/r'); //查找
		buf.duplicate();// 进行复制
		
		//5，资源释放
		//手动的释放 buf   入栈释放     出栈不用去 释放
		//ReferenceCountUtil.release(msg);//引用基数减1  （计数回收，JDK是可达回收）
		//也可以传递到下一个hannder 
		//ctx.fireChannelRead(msg);
		//6.工具类
		//ByteBufUtil.hexDump(AAADD);
	}

}
