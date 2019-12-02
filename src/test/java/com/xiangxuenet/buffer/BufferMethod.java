package com.xiangxuenet.buffer;

import java.nio.ByteBuffer;

public class BufferMethod {
  public static void main(String[] args) {
	System.out.println("---------Test get-----------");
	ByteBuffer buffer = ByteBuffer.allocate(32);
	buffer.put((byte)  'a');//0
	buffer.put((byte)  'b');//1
	buffer.put((byte)  'c');//2
	buffer.put((byte)  'd');//3
	buffer.put((byte)  'e');//4
	buffer.put((byte)  'f');//5
	System.out.println("before filp()" +buffer);
	//转换读取模式
	buffer.flip();
	System.out.println("before get()" + buffer);
	System.out.println((char)buffer.get());
	System.out.println("after get()"+ buffer);
	
	//get(index) 不影响position的 值
	System.out.println((char) buffer.get(2));
	System.out.println("after get(index):"+buffer);
	
	byte[] dst = new byte[10];
	//position 移动2位
	buffer.get(dst,0,2);
	/*这里的buffer是 abcdef[pos=3 lim=6 cap=32]*/
	System.out.println("after get(dst,0,2):"+buffer);
	System.out.println("dst value :"+new String(dst));
	buffer.flip();
	System.out.println("after flip:"+buffer);
	
	System.out.println("-------------test Put---------------------");
	ByteBuffer bb = ByteBuffer.allocate(32);
	System.out.println("before put(byte):"+ bb);
	System.out.println("after put(byte):" + bb.put((byte) 'z'));
	 // put(2,(byte) 'c')不改变position的位置
	bb.put(2, (byte) 'm');
	System.out.println("after put(2,(byte) 'm'):" + bb);
	System.out.println(new String(bb.array()));
	
	// 这里的buffer是 abcdef[pos=4 lim=6 cap=32]
	bb.put(buffer);
    System.out.println("after put(buffer):" + bb);
    System.out.println(new String(bb.array()));
    
    System.out.println("--------Test reset----------");
    buffer = ByteBuffer.allocate(20);
    buffer.put((byte) '1');
    System.out.println("buffer = " + buffer);
    buffer.clear();
    System.out.println("buffer value =" + new String(buffer.array()));
    System.out.println("buffer  clear()= " + buffer);
    buffer.position(5);//移动position到5
    buffer.mark();//记录当前position的位置
    buffer.position(10);//移动position到10
    System.out.println("before reset:" + buffer);
    buffer.reset();//复位position到记录的地址
    System.out.println("after reset:" + buffer);
    
    System.out.println("--------Test rewind--------");
    buffer.clear();
    buffer.position(10);//移动position到10
    buffer.limit(15);//限定最大可写入的位置为15
    System.out.println("before rewind:" + buffer);
    buffer.rewind();//将position设回0
    System.out.println("before rewind:" + buffer);
    
    System.out.println("--------Test compact--------");
    buffer.clear();
    //放入4个字节，position移动到下个可写入的位置，也就是4
    buffer.put("abcd".getBytes());
    System.out.println("before compact:" + buffer);
    System.out.println(new String(buffer.array()));
    buffer.flip();//将position设回0，并将limit设置成之前position的值
    System.out.println("after flip:" + buffer);
    //从Buffer中读取数据的例子，每读一次，position移动一次
    System.out.println((char) buffer.get());
    System.out.println((char) buffer.get());
    System.out.println((char) buffer.get());
    System.out.println("after three gets:" + buffer);
    System.out.println(new String(buffer.array()));
    
  //compact()方法将所有未读的数据拷贝到Buffer起始处。
    // 然后将position设到最后一个未读元素正后面。
    buffer.compact();
    System.out.println("after compact:" + buffer);
    System.out.println(new String(buffer.array()));
}
}
