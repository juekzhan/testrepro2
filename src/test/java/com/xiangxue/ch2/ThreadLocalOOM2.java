package com.xiangxue.ch2;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalOOM2 {
  private static final int TASK_LOOP_SIZE = 500;
  
  final static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<LocalVariable>();
  
  final static Executor poolExecutor = 
		  new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>()); //线程池频繁的调用
  
  static class LocalVariable{
	  @SuppressWarnings("unused")
	private byte[] a = new byte[1024*1024*5];   //5M大小
  }
  
  public static void main(String[] args) throws Exception {
	for(int i = 0;i<TASK_LOOP_SIZE ;i++) {
		poolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				localVariable.set(new LocalVariable());   //这里在回收的时候是引用 ，但是new 就是强引用，
				                                          //threadLocal 回去回收，但是里面的new对象不会回收
				                                          //所以就造成找不到 堆 栈的关联
				System.out.println("use ThreadLocal");
				localVariable.remove();     //要进行一个回收，不回收的话  你自己打卡监控看你的 jvm内存变化  //注释掉
			}
		});
		Thread.sleep(500);
	}
	System.out.println("pool exectue over");
}
}
