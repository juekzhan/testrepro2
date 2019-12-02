package com.xiangxue.ch6;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


import com.xiangxue.ch1.SleepTools;

public class UseThreadPool {
  //工作线程
   static class Worker implements Runnable{
    private String taskName;
    private Random r = new Random();
    
    public Worker(String taskName) {
    	 this.taskName = taskName;
	}
    
    public String getTaskName() {
    	return taskName;
    }
    
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() +" taskName" +taskName);
		SleepTools.secondS(r.nextInt(100)*5+3);
	}
	   
   }
   
   static class CallWorker implements Callable<String>{
	   private String taskName;
	    private Random r = new Random();
	    
        public CallWorker(String taskName){
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }    	
	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName() +" CaskName" + taskName);
		return Thread.currentThread().getName()+":"+r.nextInt(100)*5;
	}
	   
   }
   
   
   @SuppressWarnings("unused")
private static class MyThreadFactory implements ThreadFactory{
    
	   private AtomicInteger count = new AtomicInteger(0);
	   
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread("juekzhan: "+ count.getAndIncrement());
		t.setDaemon(true);
		System.out.println("create" + t);
		return t;
	}
	   
   }
   
   public static void main(String[] args) throws Exception {
	   //当线程数小于核心线程数  ，创建线程
	   //当 线程数 大于等于核心线程数 。且任务队列未满时。降任务放入队列
	   //当任务队列已满，//如线程数小于最大线程。创建线程 //如线程数大于最大线程数，走策略
	ExecutorService pool = new ThreadPoolExecutor(2, 4, 3, 
			TimeUnit.SECONDS, 
			new ArrayBlockingQueue<Runnable>(10),    //不建议使用无界队列，防止OOM  // JDK 里面的 new的几个类型的线程池不兼用使用
			//new MyThreadFactory(),    //线程池工厂，给每个线程设置属性 （在这里，设置为守护线程）
			new ThreadPoolExecutor.AbortPolicy()) {
                
		        //覆盖线程池中的  ，线程池中的线程在执行前执行
				@Override
				protected void beforeExecute(Thread t, Runnable r) {
					System.out.println("This is 之前");
				}

				 //覆盖线程池中的  ，线程池中的线程在执行后执行   //切入
				@Override
				protected void afterExecute(Runnable r, Throwable t) {
					System.out.println("This is 之后");
				}

				@Override
				protected void terminated() {
				  System.out.println("线程池中断");
				}
		
		     
		
	};     
	
	
	
	//配置线程池
	//1,cpu 计算密集型 ，核心线程数为cpu个数+1  （加密，计算） ,+1页分页，就是在内存中没调用过来的
	//2,I/O 密集型   核心数为 cpu*2 （在没有监控 的时候） （读取文件，数据库 ，网络传输）
	//3,混合类型的时候  计算密集型~I/O 分2个线程池   如果远远大于就不需要分线程池
	System.out.println("当前cpu的核心数："  + Runtime.getRuntime().availableProcessors());  //获得CPU的核数
	
	for(int i = 0;i < 8;i++) {
		Worker worker = new Worker("woker" + i);
		pool.execute(worker);
	}
	
	for(int i = 0;i<8;i++) {
		CallWorker callWorker = new CallWorker("callWorkr" + i);
		Future<String> result = pool.submit(callWorker);
		System.out.println(result.get());
	}
	
	pool.shutdown();//中断标志位释放
	//pool.shutdownNow();//强制干掉线程池（）
}
   
}
