package com.xiangxue.cha8a.demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.xiangxue.cha8a.vo.ITaskProcesser;
import com.xiangxue.cha8a.vo.TaskResult;
import com.xiangxue.cha8a.vo.TaskResultType;

public class  MyTask  implements  ITaskProcesser<Integer, Integer> {

	@Override
	public TaskResult<Integer> taskExecute(Integer data) {
		Random r = new Random();
		int flag = r.nextInt(500);
		System.out.println("flag value = " + flag);
		try {
			TimeUnit.MILLISECONDS.sleep(flag);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(flag < 300) {
			Integer returnValue = data.intValue()+flag;
			return new TaskResult<Integer>(TaskResultType.Sucess,returnValue);
		}else if(flag>301 && flag<=400) {
			return new TaskResult<Integer>(TaskResultType.Failure,-1,"Failure");
		}else {
			try {
				throw new RuntimeException("异常发生了！！");
			}catch (Exception e) {
				return new TaskResult<Integer>(TaskResultType.Exception,
						-1,e.getMessage());
			}
		}
		
	}
  
}
