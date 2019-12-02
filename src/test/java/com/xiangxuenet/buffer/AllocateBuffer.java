package com.xiangxuenet.buffer;

import java.nio.ByteBuffer;

//buffer 的分配
public class AllocateBuffer {
  public static void main(String[] args) {
	System.out.println("------------Test allocate----------------");
	System.out.println("before alocate:"+Runtime.getRuntime().freeMemory());
	
	//堆上分配 1G的内存
	ByteBuffer buffer = ByteBuffer.allocate(102400000);
	System.out.println("buffer = " + buffer);
    System.out.println("after alocate:"+ Runtime.getRuntime().freeMemory());
    
    //这部分用直接内存 分配1M
    ByteBuffer directBuffer = ByteBuffer.allocateDirect(102400);
    System.out.println("directBuffer = " + directBuffer);
    System.out.println("after direct alocate:"+ Runtime.getRuntime().freeMemory());
    
    System.out.println("----------Test wrap--------");
    byte[] bytes = new byte[32];
    //把数据 bytes 方式以数组的形式放入buffer中去
    buffer = ByteBuffer.wrap(bytes);
    System.out.println(buffer);
    
    //在上一个方法的基础上可以指定偏移量和长度，
    //这个offset也就是包装后byteBuffer的position，而length呢就是limit-position的大小，
    //从而我们可以得到limit的位置为length+position(offset)
    buffer = ByteBuffer.wrap(bytes, 10, 10);
    System.out.println(buffer);
    
}
}
