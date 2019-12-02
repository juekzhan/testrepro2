package com.xiangxue.cha8a.vo;
/**
 * @ClassName: ITaskProcesser  
 * @Package :com.xiangxue.cha8a.vo
 * @Description: 类说明：要求框架使用者实现的任务接口，因为任务的性质在调用时才知道，
 *      所以传入的参数和方法的返回值均使用泛型
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月23日 下午2:40:41
 */
public interface ITaskProcesser<T,R> {
	 TaskResult<R> taskExecute(T data);
}
