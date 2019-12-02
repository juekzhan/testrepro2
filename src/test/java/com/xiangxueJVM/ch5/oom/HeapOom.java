package com.xiangxueJVM.ch5.oom;

import java.util.LinkedList;
import java.util.List;

//堆溢出
// -Xms30M -Xmx30m 堆大小为30m -XX:+PrintGCDetails
//java.lang.OutOfMemoryError: Java heap space
// //java.lang.OutOfMemoryError: GC overhead limit exceeded，这是内存泄露
public class HeapOom {
 @SuppressWarnings("unused")
public static void main(String[] args) {
	//String [] strings =  new String[100000000]; //100M的大小异常 
	 List<Object> list = new LinkedList<>();  //GCRoots   在方法执行的时候
	 int i = 0;
	 while(true) {
		 i++;
		 if(i%10000 == 0) System.out.println("i="+i);
		 list.add(new Object());              
	 }
}
}
