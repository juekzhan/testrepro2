package com.xiangxue.cha8a_test.vo;



public interface ITaskProcesser<T, R> {
	TaskResult<R> taskExecute(T data);
}
