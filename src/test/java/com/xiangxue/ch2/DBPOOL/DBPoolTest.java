package com.xiangxue.ch2.DBPOOL;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class DBPoolTest {
     
	static DbPoolBaseEntry pool = new DbPoolBaseEntry(10);
    
	static CountDownLatch end;
	
	public static void main(String[] args) throws Exception {
		//线程数
		int threadCount = 50;
		end = new  CountDownLatch(threadCount);
		//每次线程操作的数
		int count = 20;
		AtomicInteger got  = new AtomicInteger(); //计数器  统计可以拿到的链接线程数
		AtomicInteger notGot = new AtomicInteger(); //计数器 统计没有拿到链接的线程数
		for(int i = 0 ;i<threadCount ; i++) {
			Thread thread = new Thread(new Worker(count, got, notGot));
			thread.start();
		}
		end.await(); //所有线程在此等待
		System.out.println("总共尝试了" + (threadCount * count));
		System.out.println("拿到链接的次数:" + got );
		System.out.println("没能拿到链接的次数" + notGot);
		
	}
	
	static class Worker implements Runnable{
        int count ;
        AtomicInteger got;
        AtomicInteger notGot;
        
       
		public Worker(int count, AtomicInteger got, AtomicInteger notGot) {
			this.count = count;
			this.got = got;
			this.notGot = notGot;
		}


		@Override
		public void run() {
		  while(count > 0) {
			  try {
				  //从线程池获取到链接，如果1000ms内无法取到，将会返回null
				  //分别统计链接获取的数量got和未获取到的数量notGot
				  Connection connection = pool.fetchConnection(1000);
				  if(connection != null) {
					  try {
						  connection.createStatement();
						  System.out.println("执行的线程数");
//						  PreparedStatement  preparedStatement = connection.prepareStatement("");
//						  preparedStatement.execute();
						  connection.commit();
					  }finally {
						 pool.releaseConnection(connection); 
						 got.incrementAndGet();
					  }
				  }else {
					  notGot.incrementAndGet();
					  System.out.println("等待超时..............................");
				  }
			  }catch (Exception e) {
			  }finally {
				  count --;
			  }
		  }
		  end.countDown();
		}
		
	}
}
