package com.xiangxue.cha8a_test.vo;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;


//工作内容
public class JobInfo<R> {
   @SuppressWarnings("unused")
private final String jobName;  //工作类型的名字
   
   private final int jobLenth; //工作的长度
   
   private final ITaskProcesser<?, ?> taskProcesseer;
   
   @SuppressWarnings("unused")
private final long expireTime; //保存工作线程以供查询
   
   private AtomicInteger sucessCount;
   
   private AtomicInteger taskProcessCount;
   
   private LinkedBlockingDeque<TaskResult<R>> taskDateilQueue;

public JobInfo(String jobName, int jobLenth, ITaskProcesser<?, ?> taskProcesseer, long expireTime) {
	this.jobName = jobName;
	this.jobLenth = jobLenth;
	this.taskProcesseer = taskProcesseer;
	this.expireTime = expireTime;
	sucessCount = new AtomicInteger(0);
	taskProcessCount = new AtomicInteger(0);
	taskDateilQueue = new LinkedBlockingDeque<>(jobLenth);
}
  
   public int getSucess() {
	   return sucessCount.get();
	   
   }
   
   public int getTaskCount() {
	   return taskProcessCount.get();
   }
   
   public int getFailCount() {
	   return taskProcessCount.get() - sucessCount.get();
   }

/**
 * @return the jobLenth
 */
public int getJobLenth() {
	return jobLenth;
}


public ITaskProcesser<?, ?> getTaskProcesseer() {
	return taskProcesseer;
}
   
/* 提供整体任务信息 */
public String getTotalProcess() {
	return "Sucess[" + sucessCount.get() + "]/Current[" + taskProcessCount.get() + "]  Total  [" + jobLenth + "]";
}
   
public List<TaskResult<R>> getTaskResult(){
	List<TaskResult<R>> list = new LinkedList<>();
	TaskResult<R> taskResult;
	while((taskResult = taskDateilQueue.pollFirst()) != null) {
		list.add(taskResult);
	}
	return list;
}

//处理结果  放入缓存
public void addTaskResult(TaskResult<R> taskResult) {
	if(TaskResultType.SUCESS.equals(taskResult.getResultType())) {
		sucessCount.incrementAndGet();
	}
	taskProcessCount.incrementAndGet();
	taskDateilQueue.addLast(taskResult);
	if(taskProcessCount.get() == jobLenth) {    //如果长度一样的话放入对应的数据缓存中
		//TODO  放入多缓存中去
	}
}

}
