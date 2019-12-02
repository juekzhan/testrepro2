package com.xiangxue.cha8a.vo;
/*提交给框架执行的工作类试题
 * 工作：表示本批次需要处理的 同性质任务（Task）的一个集合
 * */

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

import com.xiangxue.cha8a.CheckJobProcesser;

public class JobInfo<R> {
	// 工作名，用以区分框架中唯一的工作
	private final String jobName;
	// 工作中任务的长度
	private final int jobLength;

	private final ITaskProcesser<?, ?> taskProcesser; // 任务处理器

	private  AtomicInteger successCount;/* 任务成功对的次数 */

	private AtomicInteger taskProcessCount;/* 工作中任务目前已经处理的次数 */
	// 存放每个任务的处理结果，供查询使用
	private LinkedBlockingDeque<TaskResult<R>> taskDateilQueue;

	private final long expireTime; // 保留的工作的结果信息供查询的时长

	private static CheckJobProcesser checkJob = CheckJobProcesser.getInstance();

	public JobInfo(String jobName, int jobLength, ITaskProcesser<?, ?> taskProcesser, long expireTime) {
		super();
		this.jobName = jobName;
		this.jobLength = jobLength;
		this.taskProcesser = taskProcesser;
		this.expireTime = expireTime;
		successCount = new AtomicInteger(0);
		taskProcessCount = new AtomicInteger(0);
		taskDateilQueue = new LinkedBlockingDeque<>(jobLength);
	}

	public int getSucCount() {
		return successCount.get();
	}

	public int getTaskProcessCount() {
		return taskProcessCount.get();
	}

	public int getFailCount() {
		return taskProcessCount.get() - successCount.get();
	}

	public ITaskProcesser<?, ?> getTaskProcesser() {
		return taskProcesser;
	}

	public int getJobLenth() {
		return jobLength;
	}

	/* 提供整体任务信息 */
	public String getTotalProcess() {
		return "Sucess[" + successCount.get() + "]/Current[" + taskProcessCount.get() + "]  Total  [" + jobLength + "]";
	}

	/* 提供工作中的每个任务处理结果 */
	public List<TaskResult<R>> getTaskDetail() {
		List<TaskResult<R>> taskResultList = new LinkedList<>();
		TaskResult<R> taskResult;
		while ((taskResult = taskDateilQueue.pollFirst()) != null) {
			taskResultList.add(taskResult);
		}
		return taskResultList;
	}

	/*
	 * 每个任务处理完成后，记录任务的处理结果，因为从业务应用的角度来说， 对查询任务进度数据的一致性要不高 我们保证最终一致性即可，无需对整个方法加锁
	 */
	public void addTaskResult(TaskResult<R> taskResult) {
		if (TaskResultType.Sucess.equals(taskResult.getResultType())) {
			successCount.incrementAndGet();
		}
		taskProcessCount.incrementAndGet();
		taskDateilQueue.addLast(taskResult);
		if (taskProcessCount.get() == jobLength) {
			checkJob.putJob(jobName, expireTime);
		}
	}

}
