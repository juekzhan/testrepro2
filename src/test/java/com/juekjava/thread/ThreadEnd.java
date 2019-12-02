package com.juekjava.thread;
/**
 * 
 * @ClassName: ThreadEnd  
 * @Package :com.xiangxue
 * @Description: 线程结束  如何安全的终端线程
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2014年4月23日 下午2:11:54
 */
public class ThreadEnd {
  private static class UseThread extends Thread{
	  public UseThread(String name) {
		  super(name);
	  }

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		while(!isInterrupted()){  //判断不是中断的时候
		//while(true){	
		 System.out.println(threadName + "is run!");
		}
		System.out.println(threadName + "is interrput flg is  "+isInterrupted());
	}
  }
  
  public static void main(String[] args) throws Exception {
	Thread endThread = new UseThread("endThread");
	endThread.start();
	Thread.sleep(20);
	endThread.interrupt();
}
}
