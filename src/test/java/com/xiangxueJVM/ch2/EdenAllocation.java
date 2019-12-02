package com.xiangxueJVM.ch2;
//对象优先在Eden区分配
//  -Xms20m (最小) -Xmx20m（最大）  -Xmn10m（新生代） -XX:+PrintGCDetails
//-XX:+PrintGCDetails 打印垃圾回收日志，程序退出时输出当前内存的分配情况 
//
//大多数情况下，对象在新生代Eden区中分配。当Eden区没有足够空间分配时，虚拟机将发起一次Minor GC。
public class EdenAllocation {
     private static final int _MB  = 1024*1024 ;   //1M的大小 
	  @SuppressWarnings("unused")
	public static void main(String[] args) {
	  byte[] b1,b2,b3,b4;
	  b1 =  new byte[1*_MB];
	  b2 =  new byte[1*_MB];
	  b3 =  new byte[1*_MB];
	  b4 =  new byte[5*_MB];
   }
}
