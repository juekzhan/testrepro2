package com.xiangxue.ch2.forkjoin.pageCount;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;




public class UpdateLimitTask extends RecursiveAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8972580779324581978L;
    
	private int pageNums  = 0;
	
	private final int PAGESIZE = 1000;
	
	
	
	public UpdateLimitTask(int pageNums) {
		this.pageNums = pageNums;
	}



	@Override
	protected void compute() {
		   List<UpdateLimitTask> subTasks = new ArrayList<>();
			if(pageNums >= 1) {
			int startNum = (pageNums -1) * PAGESIZE;
			System.out.println("startNum = " + startNum +", PAGESIZE =" + PAGESIZE);
			//torderReceiptinfoMapper.pageData(startNum, PAGESIZE);
			  pageNums --;
			  UpdateLimitTask task = new UpdateLimitTask(pageNums);
			  subTasks.add(task);
			}
		
		if(!subTasks.isEmpty()) {
			for(UpdateLimitTask task : invokeAll(subTasks)) {
				task.join();
			}
		}
		
	}
	
	public static void main(String[] args) {
		try {
			ForkJoinPool pool = new ForkJoinPool();
			UpdateLimitTask task = new UpdateLimitTask(489);
			//同步提交
			pool.invoke(task);
			System.out.println("Task is Running .......");
			Thread.sleep(1);

			task.join();//阻塞方法
			System.out.println("Task End");
		}catch (Exception e) {
          e.printStackTrace();
		}
	}

}
