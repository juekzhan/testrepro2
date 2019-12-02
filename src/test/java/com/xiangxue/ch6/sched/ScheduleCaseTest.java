package com.xiangxue.ch6.sched;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleCaseTest implements Runnable{
   public final static int Long_8 = 8 ;
   public final static int short_2 = 2 ;
   public final static int Normal_5 = 5 ;
   
   public static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   
   
	@Override
	public void run() {
      System.out.println(Thread.currentThread().getName()+formater.format(new Date()));		
		
	}
   
	
	public static void main(String[] args) {
		ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(1);
		schedule.scheduleAtFixedRate(new ScheduleCaseTest(), 1000, 3000, TimeUnit.MILLISECONDS);
	}
}
