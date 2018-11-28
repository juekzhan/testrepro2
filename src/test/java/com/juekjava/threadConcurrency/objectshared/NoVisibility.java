package com.juekjava.threadConcurrency.objectshared;

import net.jcip.annotations.NotThreadSafe;

/**
 * 
 * @ClassName: NoVisibility
 * @Package :com.juekjava.threadConcurrency.objectshared
 * @Description: 内存不可见的性
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2018年11月27日 下午4:28:01
 */
@NotThreadSafe
public class NoVisibility {
	private static boolean ready = false;

	private static int number = 0;

	private static class ReadThread extends Thread {

		@Override
		public void run() {
          while(!ready) {
        	  // 举个例子：一帮朋友在排队上公交车，轮到Yield的时候，他突然说：我不想先上去了，咱们大家来竞赛上公交车。然后所有人就一块冲向公交车，
              //有可能是其他人先上车了，也有可能是Yield先上车了。
        	  Thread.yield();
        	  System.out.println(number);
          }
		}
	}
	
	public static void main(String[] args) {
		new ReadThread().start();
		number = 42;
		ready = true;
	}
	
}
