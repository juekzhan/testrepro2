package com.xiangxue.ch5.queue;

import java.util.concurrent.DelayQueue;

public class TestMain {

	public static void main(String[] args) throws InterruptedException {
		 DelayQueue<TtemVo<Order>> queue = new DelayQueue<TtemVo<Order>>();//延时队列
		 new Thread(new PutOder(queue)).start();
	     new Thread(new QueryOrder(queue)).start();
	      
	      //每隔500毫秒，打印个数字
	      for(int i=1;i<15;i++){
	          Thread.sleep(500);
	          System.out.println(i*500);
	      }
	}
   
	
	
}
