package com.xiangxueJVM.ch3;

import java.util.LinkedList;
import java.util.List;

//VM Args：-Xms30m -Xmx30m  -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError(打印堆溢出的信息)
public class Oom {
  public static void main(String[] args) {
	  //数组:

//	    优点：使用方便 ，查询效率 比链表高，内存为一连续的区域 
//
//	    缺点：大小固定，不适合动态存储，不方便动态添加
//	    链表：
//
//	     优点：可动态添加删除   大小可变   
//	     缺点：只能通过顺次指针访问，查询效率低
	List<Object> list = new LinkedList<>(); ////在方法执行的过程中，它是GCRoots
	int i = 0;
	while(true) {
		i++;
		if(i%1000 == 0 ) System.out.println("i = "+ i);
		list.add(new Object());        //这点会造成 内存的泄露 ，导致内存溢出，
	}
}
}
