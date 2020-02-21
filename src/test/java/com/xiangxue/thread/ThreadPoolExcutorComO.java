package com.xiangxue.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.xiangxue.ch1.SleepTools;


public class ThreadPoolExcutorComO {
	// 定义一个线程池 处理发送到HUB的订单
	// 就是订单完成 之后的数据
	private static ExecutorService docExecutorService = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
	// 定义一个 发送结束 存储到 数据库 的 线程
	private static ExecutorService docUploadExecutorService = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
	// 定义个完成的队列，包含线程池，和BlokingQueue ，先完成的先返回数值
	private static CompletionService<String> docCompltionService = new ExecutorCompletionService<>(
			docExecutorService);
	private static CompletionService<String> docUpCompltionService = new ExecutorCompletionService<>(
			docUploadExecutorService);

	//发送HUB的线程
	private static class MakeHub implements Callable<String>{
        private final String orderId ; 
		
		public MakeHub(String orderId) {
			this.orderId = orderId;
		}
		@Override
		public String call() throws Exception {
		  System.out.println("订单"+orderId+"发送Hub");
		  SleepTools.second(5);
		  return orderId;
		}
	}
	
	// 存数据库线程
	private static class InDb implements Callable<String>{
		
		  private final String orderId ; 
			public InDb(String orderId) {
				this.orderId = orderId;
			}
		
		@Override
		public String call() throws Exception {
			 System.out.println("订单"+orderId+"存DB");
			 SleepTools.secondS(50);
			return null;
		}
		
	}
	
	//线程 一直去往数据 库中存
	@SuppressWarnings("unused")
	private static class InDbTask implements Runnable{
        
		  private final CompletionService<String> docUpCompltionService ; 
		
		  
		  
		public InDbTask(CompletionService<String> docUpCompltionService) {
			super();
			this.docUpCompltionService = docUpCompltionService;
		}

		@Override
		public void run() {
			while(true) {
				Future<String> future = null;
				try {
					future = docUpCompltionService.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 try {
					System.out.println("订单"+future.get()+"存DB");
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 SleepTools.secondS(50);
			}
			
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		int count = 50000;
		for(int i = 0; i < count; i++) {
			docCompltionService.submit(new MakeHub(i+""));
		}
		for(int i =0 ; i<count ;i++) {
			Future<String> future = docCompltionService.take();
			docUpCompltionService.submit(new InDb(future.get()));
		}
//		InDbTask task = new InDbTask(docCompltionService);
//		new Thread(task).run();
		System.out.println("运行时间长" + (System.currentTimeMillis() - start)/1000+"秒");
	}
   
}
