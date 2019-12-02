package com.xiangxue.ch1;

public class EndRunnable5 {
   private static class EndRunnable5Inside implements Runnable{
      
   public EndRunnable5Inside() {
	  
	}
	   
	@Override
	public void run() {
		  System.out.println(Thread.currentThread().getName() + ": frist : interruptEd flg is " + Thread.currentThread().isInterrupted());
		  while(!Thread.currentThread().isInterrupted()) {     //显示中断标志位 
			  //while(!Thread.interrupted()) {  //显示中断标志 为，并且复位中断标志位
			  // while(true) {
				  System.out.println(Thread.currentThread().getName() + ": interruptEd flg is " + Thread.currentThread().isInterrupted());
			  }
		  System.out.println(Thread.currentThread().getName() + ": last: flag: " +Thread.currentThread().isInterrupted());
		
	}
	   
   }
   
   @SuppressWarnings("static-access")
public static void main(String[] args) throws Exception {
	   EndRunnable5Inside endRunnable5Inside = new EndRunnable5Inside();
	   Thread runnable = new Thread(endRunnable5Inside); 
	   runnable.start();
	   runnable.sleep(1000);
	   runnable.interrupt();
}
}
