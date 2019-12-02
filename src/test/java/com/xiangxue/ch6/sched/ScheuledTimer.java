package com.xiangxue.ch6.sched;


import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ScheuledTimer {

	public static void main(String[] args) {
		ScheduledThreadPoolExecutor schedule  = new  ScheduledThreadPoolExecutor(1);
        
		//延时间隔   延时1秒 间隔3秒去执行池子里的任务
		//schedule.scheduleAtFixedRate(new ScheduleWorker(ScheduleWorker.ProcessException), 1000, 3000, TimeUnit.MILLISECONDS);
		
		//执行有异常的 任务，必须要捕获异常才能运行
		schedule.scheduleAtFixedRate(new ScheduleWorker(ScheduleWorker.Normal), 1000, 3000, TimeUnit.MILLISECONDS);
	     
		
	}
	
	

}
