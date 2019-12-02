package com.xiangxue.ch2.countdownlatch;

import java.util.concurrent.CountDownLatch;

import com.xiangxue.ch1.SleepTools;
/**
 * 
 * @ClassName: UseCountDownLatch  
 * @Package :com.xiangxue.ch2.countdownlatch
 * @Description: 业务线程   和 初始化线程
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年5月9日 上午11:42:48
 */
public class UseCountDownLatch {
     //定义初始值为6 进行计算
	static CountDownLatch latch = new CountDownLatch(6);
    //定义一个初始化线程
	private static class InitThread  implements Runnable{

		@Override
		public void run() {
         System.out.println("Thread_" + Thread.currentThread().getId() +" ready init work .....");
         //计算器减少1
         latch.countDown();
         for(int i=0 ;i<2;i++) {
        	 System.out.println("Thread_" + Thread.currentThread().getId() + "........continue do its work");
         }
		}
		
	}
	//业务线程 等待latch的计数器为 0 完成
	private static class  BusiThread implements Runnable{
		
		
		@Override
		public void run() {
            try {
				latch.await();    //在latch 为0的时候唤醒此线程
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(int i = 0 ;i< 3;i++) {
				System.out.println("BusiThread_"+ Thread.currentThread().getId() +"do business ......");
			}
		}
		
	}
   
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
          			
			@Override
			public void run() {
			 SleepTools.second(1);
			 System.out.println("Thread_"+ Thread.currentThread().getId() + " ready init work step lst ...");
			 latch.countDown(); //
			 System.out.println("begin  step 2nd ......");
			 SleepTools.second(1);
			 System.out.println("Thread_" + Thread.currentThread().getId() + "ready init work  step 2nd.....");
			 latch.countDown();
			}
		}).start();
		
		new Thread(new  BusiThread()).start();
		
		for(int i  = 0 ;i<= 3 ;i++) {
			new Thread(new InitThread()).start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main do ites work.....");
	}
}
