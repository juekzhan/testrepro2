package com.xiangxue.ch6.comps;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class CompletionCase {
   private final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
   
   private final int TOTAL_TASK = Runtime.getRuntime().availableProcessors()*10;
   
   public void testByQueue() throws Exception{
	   long start = System.currentTimeMillis();
	   AtomicInteger count = new AtomicInteger(0);
	   
	   //創建线程池
	   ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
	   BlockingQueue<Future<Integer>> queue = new LinkedBlockingQueue<>();
	   
	   
	   for(int i = 0 ;i<TOTAL_TASK;i++) {
		   Future<Integer> future = pool.submit(new WorkTask("ExceTask" + i));
		   queue.add(future);
	   }
	   
	   //检查线程执行的结果
	   for(int i = 0 ;i<TOTAL_TASK;i++) {
		   int slepTime = queue.take().get();
		   count.addAndGet(slepTime);
	   }
	   
	   pool.shutdown();
	   
	   System.out.println("-----------takes sleep time "+count.get() +
			   "ms and spend time:" + (System.currentTimeMillis() - start)+" ms");
   }
   
   @SuppressWarnings("unchecked")
public void testComs() throws Exception{
	   long start = System.currentTimeMillis();
	   AtomicInteger count = new AtomicInteger(0);
	   //創建线程池
	   ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
	   //换掉
	   @SuppressWarnings("rawtypes")
	CompletionService cService   = new ExecutorCompletionService<>(pool);
	   
	   for(int i = 0 ;i<TOTAL_TASK;i++) {
		   cService.submit(new WorkTask("ExceTask" + i));
		  
	   }
	   
	   for(int i = 0 ;i<TOTAL_TASK;i++) {
		   int sleptTime = (int) cService.take().get();
		   count.addAndGet(sleptTime);
	   }
	   
       pool.shutdown();
	   
	   System.out.println("-----------takes sleep time "+count.get() +
			   "ms and spend time:" + (System.currentTimeMillis() - start)+" ms");
   }
   
   public static void main(String[] args) throws Exception {
	 CompletionCase completionCase = new CompletionCase();
	 completionCase.testByQueue();
	 completionCase.testComs();
}
}
