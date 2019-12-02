package com.juekjava.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadCuu extends Thread {
    private static  class UseCall implements Callable<String> {

		@Override
		public String call() throws Exception {
			System.out.println("I ,m Callable");
			return "THIS IS";
		}
    	
    }
    
    @SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		UseCall use = new UseCall();
		FutureTask<String> futureTask  = new FutureTask<>(use);
		Thread t = new Thread(futureTask);
		t.start();//运行线程
		//t.stop();   //强制中断 ，造成资源丢失
		//stop()，resume(),suspend()已不建议使用，stop()会导致线程不会正确释放资源，suspend()容易导致死锁
		// 这是线程协作式
		t.interrupt(); //中断一个线程，
	    t.interrupted() ;//判断当前线程是否属于中断状态
	    
		System.out.println(futureTask.get());
		
	}
}
