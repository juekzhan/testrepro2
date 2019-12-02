package com.xiangxueJVM.ch5;

import java.util.LinkedList;
import java.util.List;


//堆内存溢出
//VM Args：-Xms30m -Xmx30m  -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError (打印堆溢出的错误)
public class DumpOom {
  public static void main(String[] args) {
	List<Object> list = new LinkedList<>(); //GCROOTS
	int i = 0;
	while(true) {
		i++;
		if(i%10000  == 0) System.out.println("i="+i);
		list.add(new Object());
	}
}
}
