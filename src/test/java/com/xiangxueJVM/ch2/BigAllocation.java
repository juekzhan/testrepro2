package com.xiangxueJVM.ch2;
//大对象直接进入老年代
//-Xms20m
//-Xmx20m
//-Xmn10m
//-XX:+PrintGCDetails
//-XX:PretenureSizeThreshold=4m
//-XX:+UseSerialGC
// -XX:PretenureSizeThreshold=4m   设置超过= 4M的对象直接进入老年代区域
//-XX:+PrintGCDetails 打印垃圾回收日志，程序退出时输出当前内存的分配情况 
//-XX:+UseSerialGC使用串行回收器进行回收，这个参数会使新生代和老年代都使用串行回收器，
//新生代使用复制算法，老年代使用标记-整理算法。Serial收集器是最基本、历史最悠久的收集器，它是一个单线程收集器。一旦回收器开始运行时，整个系统都要停止。Client模式下默认开启，其他模式默认关闭。
public class BigAllocation {
	private static final int _1MB =1024*1024; //1M的大小
	 // * 大对象直接进入老年代(Eden  2m  +1 )
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		  byte[] b1,b2,b3;
	        b1 = new byte[1*_1MB]; //这个对象在eden区
	        b2 = new byte[1*_1MB]; //这个对象在eden区
	        b3 = new byte[5*_1MB];//这个对象直接进入老年代
	        b3 = new byte[5*_1MB];//这个对象直接进入老年代
	        b3 = new byte[5*_1MB];//这个对象直接进入老年代
	        b3 = new byte[5*_1MB];//这个对象直接进入老年代
	        b3 = new byte[5*_1MB];//这个对象直接进入老年代
	        b3 = new byte[5*_1MB];//这个对象直接进入老年代
	        b3 = new byte[5*_1MB];//这个对象直接进入老年代
	        b3 = new byte[5*_1MB];//这个对象直接进入老年代
	        b3 = new byte[5*_1MB];//这个对象直接进入老年代

	}
}
