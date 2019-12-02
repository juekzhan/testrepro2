package com.xiangxue.ch1;

public class UseJoin8 {
  static class Godess implements Runnable{
     private Thread thread;
     
      
     public Godess(Thread thread) {
       this.thread = thread;
     }
     
     
     public Godess() {
	}
     
	@Override
	public void run() {
		
        System.out.println("Godess 开始排队打饭........");
		try {
         if(thread!= null) thread.join();			
		}catch (Exception e) {
		}
		SleepTools.second(2);
		System.out.println("Godess 打饭完成");
	}
	  
  }
  
  
  static class GodessBoyfriend implements Runnable{

	@Override
	public void run() {
		SleepTools.second(2);
		System.out.println("Godess friend 开始排队打饭........");
		SleepTools.second(1);
		System.out.println("Godess friend 排队打饭完成");
	}
	  
  }
  
  public static void main(String[] args) throws Exception {
	//Thread lison  = Thread.currentThread();
	
	GodessBoyfriend godessBoyfriend = new GodessBoyfriend();
	Thread godBoyThread = new Thread(godessBoyfriend);
	
	Godess godess = new Godess(godBoyThread);
	Thread g = new Thread(godess);
	//lison.start();
	g.start();
	godBoyThread.start();
	System.out.println("lison 开始打饭........");
	g.join();
	SleepTools.second(2);
	System.out.println(Thread.currentThread().getName() + " lison 打饭完成");
}
}
