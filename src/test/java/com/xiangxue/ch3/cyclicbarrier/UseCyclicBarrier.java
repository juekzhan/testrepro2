package com.xiangxue.ch3.cyclicbarrier;
/**
 * 
 * @ClassName: UseCyclicBarrier  
 * @Package :com.xiangxue.ch3.cyclicbarrier
 * @Description: CyclicBarrier 等到线程，可以反复的调用，
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年5月7日 上午11:04:58
 */

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class UseCyclicBarrier {
	//初始化等待4个线程都完成启动 (注意 后面的4 必须和线程数一样的)
   //private static CyclicBarrier barrier = new CyclicBarrier(4);
	//也就是后面的4个线程结束后，可以继续汇总后面的线程数
	// countDownLatch  是有外面的线程控制
	// 这个是线程内部相互控制
   private static CyclicBarrier barrier = new CyclicBarrier(4,new CollectThread());
   
   
   //存放子线程的工作容器
   private static ConcurrentHashMap<String, Long> resultMap = new ConcurrentHashMap<>();
   
   public static void main(String[] args) {
	for(int i =0 ;i <4; i++) {
		Thread thread = new Thread(new SubThread());
		thread.start();
	}
}
   //汇总的线程
   private static class CollectThread  implements Runnable{

	@Override
	public void run() {
		StringBuilder result = new StringBuilder();
		for(Map.Entry<String, Long> workResult : resultMap.entrySet()) {
			result.append("["+workResult.getValue()+"]");
		}
		System.out.println(" the result = "+result);
		System.out.println("汇总线程汇总完毕");
	}
	   
   }
   //相互等待的子线程
   private static class SubThread implements  Runnable{

	@Override
	public void run() {
     long id  = Thread.currentThread().getId();
     resultMap.put(Thread.currentThread().getId()+"", id);
     try {
    	 Thread.sleep(1000+id);
    	 System.out.println("Thread_" + id +"......do something ");
    	 
    	 //子线程上等待
    	 barrier.await(); // 所有的线程等待完成以后然唤醒
    	 Thread.sleep(1000+id);
    	 System.out.println("Thread_" + id +"......do its business ");
    	 
    	 barrier.await(); //再次等待 ，可以反复调用
    	 System.out.println("Thread do thread 2nd over");
     }catch (Exception e) {
       e.printStackTrace();
     }
	}
	   
   }
}
