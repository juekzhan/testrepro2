package com.xiangxue.cha8a_test.vo;

public class TaskResult<R> {
	private final TaskResultType resultType; // 方法执行返回的结果

	private final R returnValue; // 方法执行返回的结果

	private final String reason; // 如果失败可以写原因

	public TaskResult(TaskResultType resultType, R returnValue, String reason) {
		this.resultType = resultType;
		this.returnValue = returnValue;
		this.reason = reason;
	}

	public TaskResult(TaskResultType resultType, R returnValue) {
		this.resultType = resultType;
		this.returnValue = returnValue;
		this.reason = "sucess";
	}

	public TaskResultType getResultType() {
		return resultType;
	}
	/**
	 * @return the returnValue
	 */
	public R getReturnValue() {
		return returnValue;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	@Override
	public String toString() {
		return "TaskResult [resultType=" + resultType + ", returnValue=" + returnValue + ", reason=" + reason + "]";
	}

	
    
	
	
}
