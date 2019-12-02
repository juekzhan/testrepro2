package com.xiangxue.ch1;

public class StartAndRun7 {
  public static class OneThread extends Thread{
	  public void run() {
		  int sum = 0;
		  while(sum < 50) {
			  try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("This. is sum ="+(sum++));
		  }
		  System.out.println("This ThreadName:"+Thread.currentThread().getName());
	  }
  }
  
  public static void main(String[] args) {
	OneThread oneThread = new OneThread();
	oneThread.setName("juekThread");
	oneThread.start();
	//oneThread.start(); 两个start（） 会报出异常信息
	//oneThread.run(); 这个只是运行一个方法 走的是主线程 main
}
}
