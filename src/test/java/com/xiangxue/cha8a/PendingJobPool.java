package com.xiangxue.cha8a;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.xiangxue.cha8a.vo.ITaskProcesser;
import com.xiangxue.cha8a.vo.JobInfo;
import com.xiangxue.cha8a.vo.TaskResult;
import com.xiangxue.cha8a.vo.TaskResultType;

public class PendingJobPool {
	/*CPU 运行的时的线程池，与机器的cpu相同*/
 private static final  int THREAD_COUNTS = 
		 Runtime.getRuntime().availableProcessors();
 /*线程池，固定大小，有界队列 */
 private static BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(5000);
 
 private static ExecutorService taskExecutor = 
		 new ThreadPoolExecutor(THREAD_COUNTS,THREAD_COUNTS,60,TimeUnit.SECONDS,taskQueue);
 
 //工作信息存放的容器
 private static ConcurrentHashMap<String, JobInfo<?>> jobInfoMap = new ConcurrentHashMap<String, JobInfo<?>>();
 
 public static Map<String,JobInfo<?>> getMap(){
	 return jobInfoMap;
 }
 
 /*以单例模式启动*/
 private PendingJobPool() {}
 
 private static class  JobPoolHolder{
	 public static PendingJobPool pool = new PendingJobPool();
 }
 
 public static  PendingJobPool getInstance() {
	 return JobPoolHolder.pool;
 }
 /*对工作中的任务进行包装，提交给线程池使用，
	并将处理任务的结果，写入缓存以供查询*/
 private static class PendingTask<T,R> implements Runnable{
	 private JobInfo<R>  jobInfo;
	 
	 private T processData;
	 
	public PendingTask(JobInfo<R> jobInfo, T processData) {
		this.jobInfo = jobInfo;
		this.processData = processData;
	}



	public void run() {
		R r = null;
		@SuppressWarnings("unchecked")
		ITaskProcesser<T, R> taskProcesser
		= (ITaskProcesser<T, R>)jobInfo.getTaskProcesser();
		TaskResult<R> result = null;
		
		try {
			result  = taskProcesser.taskExecute(processData);
			if(result == null) {
				result  =  new  TaskResult<R>(TaskResultType.Exception, r, "result  is null");
			}
			if(result.getResultType() == null) {
				if(result.getReason() == null) {
					result = new TaskResult<R>(TaskResultType.Exception, r, "result  is null");
				}else {
					result = new TaskResult<R>(TaskResultType.Exception, r, "result is null"+result.getReason());
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			result = new TaskResult<R>(TaskResultType.Exception, r, e.getMessage());
		}finally {
			jobInfo.addTaskResult(result);
		}
	}
}
    //调用者提交工作中的任务
 public <T,R> void putTask(String jobName,T t){
		JobInfo<R> jobInfo = getJob(jobName);
		PendingTask<T,R> task = new PendingTask<>(jobInfo,t);
		taskExecutor.execute(task);
	}
 
 
	//调用者注册工作，如工作名，任务的处理器等等
	public <R> void registerJob(String jobName, int jobLength,
                             ITaskProcesser<?, ?> taskProcesser, long expireTime) {
		JobInfo<R> jobInfo = new JobInfo<>(jobName, jobLength, taskProcesser, expireTime);
		if(jobInfoMap.putIfAbsent(jobName, jobInfo)!=null) {
			throw new RuntimeException(jobName+"已经注册！");
		}
		
	}
 
 /*根据工作名称检索工作*/
	@SuppressWarnings("unchecked")
	private <R> JobInfo<R> getJob(String jobName){
		if(!jobInfoMap.containsKey(jobName)) {
			return null;
		}
		JobInfo<R> jobInfo = (JobInfo<R>) jobInfoMap.get(jobName);
		if (null==jobInfo) 
			throw new RuntimeException(jobName+"是非法任务！");	
		return jobInfo;
	}
	
	/*获得工作的整体处理进度*/
	public <R> String getTaskProgess(String jobName) {
		JobInfo<R> jobInfo = getJob(jobName);
		return jobInfo.getTotalProcess();
	}
	

	/*获得每个任务的处理详情*/
	public <R> List<TaskResult<R>> getTaskDetail(String jobName){
		JobInfo<R> jobInfo = getJob(jobName);
		return jobInfo.getTaskDetail();
	}
}
