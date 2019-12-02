package com.xiangxue.ch4.aqs;

import java.util.concurrent.locks.Lock;

import com.xiangxue.ch1.SleepTools;

public class TestMyLock {
   public void test() {
	   final Lock lock = new UseMyAQS();
	   class Worker extends Thread {

		@Override
		public void run() {
			lock.lock();
			System.out.println(Thread.currentThread().getName());
			try {
				SleepTools.second(2);
			}finally {
				lock.unlock();
			}
		}
		   
	   }
	   //启动4个子线程
	   for(int i = 0;i < 4; i++) {
		   Worker w = new Worker();
		   w.start();
	   }
	   for(int i = 0 ;i<10 ;i++) {
		   SleepTools.second(1);
	   }
   }
   public static void main(String[] args) {
	   TestMyLock testMyLock = new TestMyLock();
	   testMyLock.test();
}
}
