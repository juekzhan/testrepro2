package com.xiangxue.ch6.sched;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleWorker implements Runnable{
   
	public final static int Normal  = 0; //普通任务类型
	public final static int HasException = -1;//会抛出任务异常的任务类型
	public final static int ProcessException = 1;//抛出异常但会捕捉的任务类型
	
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

	
	private int taskType;
	
	public ScheduleWorker(int taskType) {
		this.taskType = taskType;
	}



	@Override
	public void run() {
		if(taskType == HasException) {
			System.out.println(format.format(new Date()+" Exception made"));
			throw new RuntimeException();
		}else if(taskType == ProcessException) {
			try {
				System.out.println(format.format(new Date()+"Exception made but cath"));
				throw new  RuntimeException();
			}catch (Exception e) {
                System.out.println("Exception be catched");
			}
		}else {
			System.out.println("Normal ...." + format.format(new  Date() + "this is Normal"));
		}
		
	}

}
