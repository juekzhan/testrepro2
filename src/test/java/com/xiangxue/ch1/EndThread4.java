package com.xiangxue.ch1;

public class EndThread4 {
  private static class EndThread4Inside extends Thread{
	  
	  public EndThread4Inside(String name) {
		  super(name);
	  }
	  
	  public void run() {
		  System.out.println(Thread.currentThread().getName() + ": frist : interruptEd flg is " + isInterrupted());
		  while(!isInterrupted()) {     //显示中断标志位 
		  //while(!Thread.interrupted()) {  //显示中断标志 为，并且复位中断标志位
		  // while(true) {
			  System.out.println(Thread.currentThread().getName() + ": interruptEd flg is " + isInterrupted());
		  }
		  System.out.println(Thread.currentThread().getName() + ": last: flag: " +isInterrupted());
	  }
  }
  
  
  public static void main(String[] args) throws Exception {
	  EndThread4Inside thread4Inside = new EndThread4Inside("juekThread");
	  thread4Inside.start();
	  Thread.sleep(1000);
	 // thread4Inside.stop();
	  thread4Inside.interrupt();  //改变中断标志位 为true  优雅的释放 线程共享资源
}
 }
