package com.xiangxue.cha8a.demo;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.xiangxue.cha8a.PendingJobPool;
import com.xiangxue.cha8a.vo.TaskResult;

public class AppTest {
   private final static String JOB_NAME = "计算数值";
   
   private final static int JOB_LENGTH = 500;
   
   
   //查询任务进度的线程
   private static class QueryResult implements Runnable{
      private PendingJobPool pool;
      
      public QueryResult(PendingJobPool pool) {
    	  this.pool = pool;
	}
	@Override
	public void run() {
		int i = 0;
		while(i<500) {
			List<TaskResult<String>> taskDetail = pool.getTaskDetail(JOB_NAME);
			if(!taskDetail.isEmpty()) {
				System.out.println(pool.getTaskProgess(JOB_NAME));
				System.out.println(taskDetail);
			}
			try {
				TimeUnit.MILLISECONDS.sleep(100); //休眠 毫秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}
	   
   }
   
	public static void main(String[] args) {
       MyTask myTask = new MyTask();
       Random r = new Random();
       PendingJobPool  pool = PendingJobPool.getInstance();
       pool.registerJob(JOB_NAME, JOB_LENGTH, myTask, 5);
       for(int i =0 ;  i<JOB_LENGTH;i++) {
    	   pool.putTask(JOB_NAME, r.nextInt(1000));
       }
       Thread t = new Thread(new QueryResult(pool));
       t.start();
//      Thread thread = new Thread(new FetchJob());
//   	thread.setDaemon(true);
//   	thread.start();
//   	System.out.println("开始检查守护线程.............");
	}

}
