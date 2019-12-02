package com.xiangxue.cha8a.vo;

/**
 * 
 * @ClassName: TaskResult
 * @Package :com.xiangxue.cha8a.vo
 * @Description: 任务处理后返回的结果类型
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月23日 下午2:34:42
 */
public class TaskResult<R> {
	private final TaskResultType resultType; // 方法执行的结果

	private final R returnValue; // 方法执行后返回的结果数据

	private final String reason;// 如果方法失败 ，可以填写原因

	public TaskResult(TaskResultType resultType, R returnValue, String reason) {
		super();
		this.resultType = resultType;
		this.returnValue = returnValue;
		this.reason = reason;
	}
    
	public TaskResult(TaskResultType resultType, R returnValue) {
		super();
		this.resultType = resultType;
		this.returnValue = returnValue;
		this.reason = "Success";
	}
	
	
	public TaskResultType getResultType() {
		return resultType;
	}

	public R getReturnValue() {
		return returnValue;
	}

	public String getReason() {
		return reason;
	}

	@Override
	public String toString() {
		return "TaskResult [resultType=" + resultType + ", returnValue=" + returnValue + ", reason=" + reason + "]";
	}

}
