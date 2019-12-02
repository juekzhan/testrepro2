package com.xiangxue.ch6.myselfPoll;
/**
 * 
 * @ClassName: MyThreadPool2  
 * @Package :com.xiangxue.ch6.myselfPoll
 * @Description: 自定义线程池
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月8日 下午3:34:49
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPool2 {
   //线程池中默认的线程数
	private static int WORK_NUM = 5;
   
	//队列默认任务个数为100
	private static int TASK_COUNT = 100;
	
	//工作线程组
	private WorkThread[] workThreads;
	
	//任务队列作为一个缓冲
	private  BlockingQueue<Runnable> taskQueue;
	private int worker_num; ////用户在构造这个池，希望的启动的线程数
	
	
	public MyThreadPool2() {
		this(WORK_NUM,TASK_COUNT);
	}
    
	public MyThreadPool2(int worker_numIn,int takeCountIn) {
		if(worker_numIn<=0) {
			this.worker_num = WORK_NUM;
		}else {
			this.worker_num = worker_numIn;
		}
		if(takeCountIn<=0) {
			takeCountIn = TASK_COUNT;
		}
		taskQueue = new ArrayBlockingQueue<>(takeCountIn);
		workThreads = new WorkThread[worker_num];
		for(int i = 0 ;i<worker_num;i++) {
			workThreads[i] = new WorkThread();
			workThreads[i].start();
		}
		System.out.println(Runtime.getRuntime().availableProcessors());  //获得CPU的核数
	}

    //执行任务队列
	//执行任务,其实只是把任务加入任务队列，什么时候执行有线程池管理器决定
	public void execute(Runnable task) {
		try {
			taskQueue.put(task);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

   //销毁线程池，该方法保证在所有线程都完成的情况下消除。否则等待任务完成销毁
	public void destory() {
//		T
	System.out.println("ready close pool .....");
		for(int i = 0 ;i<worker_num;i++) {
			workThreads[i].stopWorker();
			workThreads[i]= null;   //help gc
		}
		taskQueue.clear();
	}

	@Override
	public String toString() {
		return "WokerThread  number:"+worker_num +"wait task number" + taskQueue.size();
	}






	private class WorkThread extends Thread{

		@Override
		public void run() {
			Runnable r = null;
			try {
				while(!interrupted()) {
					r = taskQueue.take(); //没有的话  ，队列在此阻塞  休眠
					if(r != null) {
						System.out.println(getId() + "ready exec ："+r);
						r.run();
					}
					r = null;//help gc
						
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		public void stopWorker() {
			interrupt();
		}
	}
	
	public static void main(String[] args) {
		MyThreadPool2 pool = new MyThreadPool2(5,0);
		
		pool.execute(new MyThread("王业鑫"));
		pool.execute(new MyThread("春B"));
		pool.execute(new MyThread("蔡迪波0"));
		pool.execute(new MyThread("蔡迪波1"));
		pool.execute(new MyThread("蔡迪波2"));
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.execute(new MyThread("juek"));
		pool.destory();
	}
	

}




class MyThread implements Runnable {
	private String nameThread;
	
	public MyThread(String nameThread) {
	 this.nameThread = nameThread;
	}

	public void run() {
		System.out.println("Threadname = "+ nameThread +"is running");
		
	}
}
