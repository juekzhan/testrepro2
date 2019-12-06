package com.juekjava;

public class StopTask extends Thread
 {
   //为什么这个volatile 关键字要 不要 都不受影响呀 郁闷中
	private volatile boolean pleaseStop = true;
	
	//private boolean pleaseStop = true;
	@Override
	public void run() {
		System.out.println(pleaseStop + ": this is flag W");
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(!pleaseStop) {
			System.out.println(pleaseStop + ": this is flag N");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Task is running。。。。。。");
		}
	}
	
   public void tellMeToStop() {
	   pleaseStop = false;
   }
   
   public static void main(String[] args)  throws Exception{
	StopTask stopTask = new StopTask();
	ChangeStats change = new ChangeStats(stopTask);
	change.start();
	stopTask.start();
	
	Thread.sleep(30000);
   }
   
}


class  ChangeStats extends Thread{
    private StopTask task;
    
	public ChangeStats(StopTask task) {
		this.task = task;
	}



	@Override
	public void run() {
	   try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
	   task.tellMeToStop();
	   System.out.println("Change is running。。。。。。");
	}
	
}
