package com.xiangxue.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.xiangxue.ch1.SleepTools;

public class ThreadTest {
     
	//static CountDownLatch latch = new CountDownLatch(100);
	
	
	public static void main(String[] args) throws Exception {
		
		ExecutorService pool = Executors.newFixedThreadPool(64*2);
		CompletionService<Integer> aa = new ExecutorCompletionService<Integer>(pool);
		long start = System.currentTimeMillis();
		System.out.print("开始时间"+start);
		List<Future<Integer>> futures = new ArrayList<>();
		for(int i = 0 ; i< 5000;i++) {
			futures.add(aa.submit(() -> aaaa()));
		}
		for(@SuppressWarnings("unused") Future<Integer> future : futures) {
			aa.take().get();
		}
		System.out.print("结束时间:" +(System.currentTimeMillis() - start));
//		}
//		pool.shutdown();
//		while(true) {
//		if(pool.isTerminated()) {
//			System.out.println("运行时间长" + (System.currentTimeMillis() - start)/1000+"秒");
//			break;
//		}
//		}
	}
   
	
	private static Integer aaaa() throws InterruptedException {
//		System.out.println("订单"+no+"运行。。。。。。");
		Thread.sleep(500);
		return 1;
	}
	
	
	public static class ThreadBuss implements Runnable {
		
	private final int no;
		
	public ThreadBuss(int num) {
		this.no = num;
	}

	@Override
	public void run() {
	    System.out.println("订单"+no+"运行。。。。。。");
		SleepTools.second(5);
		
	}
	}
}
