package com.xiangxue.ch3.semaphore;


import java.sql.Connection;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

import com.xiangxue.ch1.SleepTools;

/**
 * 
 * @ClassName: DbPoolSemaphore  
 * @Package :com.xiangxue.ch3.semaphore
 * @Description: 用 semaphore 做流量控制 的线程池  流量控制
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年5月7日 下午3:13:39
 */
public class DbPoolSemaphore {
	
	
    //线程池的大小
	private static final int POOL_SIZE = 10;
	
	//线程池存放的容器
	private static LinkedList<Connection> pool = new LinkedList<>();
	
	//初始化线程池数
	static {
		for(int i = 0 ; i< POOL_SIZE ; i++) {
			pool.add(new SqlConnectionS());
		}
		System.out.println(pool.size()+"线程池的长度");
	}
   //可用链接，和 不可用链接 信号量控制的
	private Semaphore useful,unuseful;
	
	public DbPoolSemaphore() { 
		//相当于10个许可证  10个链接   这里有个坑 ，就是后面的信号量 可以无限的床架增加，所以用一正一反去控制整个信号量
		useful = new Semaphore(10);
		
		unuseful = new Semaphore(0);
	}
	
	//归还链接
	public void returnConnn(Connection connection) throws Exception{
		if(connection != null) {
			System.out.println("当前有："+ useful.getQueueLength()+"个线程等待链接~！！  " +
		         "可用的链接数："+ (useful.availablePermits()+1));
			unuseful.acquire();   //拿出一个不可用的许可证，如果没有就继续等待
			synchronized (pool) {
				pool.addLast(connection);
			}
			useful.release();   // 归还 放入一个许可证
		}
	}
	
	//从池子里面拿线程
	  public Connection takeConnect() throws Exception{
		  useful.acquire();  //拿出来一个许可证，如果没有许可证  线程就在此等待  
		  Connection connection = null;
		  synchronized(pool) {
			  connection = pool.removeFirst();
		  }
		  unuseful.release();  //不可用就归还 一个许可证
		  return connection;
	  }
	
	 private static class UseThread implements Runnable{
		 private static DbPoolSemaphore dbPool = new DbPoolSemaphore(); 
		@Override
		public void run() {
			Random r = new Random();   //让每个线程持有链接的时间不一样
			long start = System.currentTimeMillis();
			try {
     			Connection conne = dbPool.takeConnect();
     			if(conne != null) {
     			System.out.println("Thread_" + Thread.currentThread().getId()
     					          +"_获取数据库链接共耗时【" + (System.currentTimeMillis() - start) +"】ms");
				SleepTools.secondS(100 + r.nextInt(100));   //用的时间
     			}
				System.out.println("数据库查询完成归还链接");
				dbPool.returnConnn(conne);
			}catch (Exception e) {
               e.printStackTrace();
			}
		}
	 }
	 
	 public static void main(String[] args) {
		for(int i = 0 ; i< 50 ;i++) {
			new Thread(new UseThread()).start();
		}
	}
}
