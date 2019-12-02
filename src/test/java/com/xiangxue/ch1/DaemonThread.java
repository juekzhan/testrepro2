package com.xiangxue.ch1;

public class DaemonThread {
  private static class UseThread  extends Thread{
	  public void run() {
		  try {
			  while(!isInterrupted()) {
				  System.out.println(Thread.currentThread().getName() + " I am extends Thread");
			  }
			  System.out.println(Thread.currentThread().getName()  + " interrupt flag is "+ isInterrupted());
		  }finally {
			  //守护线程不一定起作用
			  System.out.println("............finally");
		  }
	  }
  }
  
  public static void main(String[] args) throws Exception {
	UseThread useThread = new UseThread();
	//useThread.setPriority(1);   //设置线程的优先级 ，取值范围 1 -10 默认为5
	useThread.setDaemon(true);
	useThread.start();
	Thread.sleep(200);
	//useThread.interrupt();
}
}
