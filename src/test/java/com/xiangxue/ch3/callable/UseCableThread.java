package com.xiangxue.ch3.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 
 * @ClassName: UseCableThread  
 * @Package :com.xiangxue.ch3.callable
 * @Description: 线程的另一种 有返回值的用法
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年5月7日 下午5:25:34
 */
public class UseCableThread implements Callable<Integer> {
    private int sum;
	
	@Override
	public Integer call() throws Exception {
		//SleepTools.second(2);
		System.out.println("Callable 子线程计算");
		for(int i = 0;i<= 500;i++ ) {
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("线程中断");
				return null;
			}
			sum = sum + i;
		}
		System.out.println("计算结果为：" + sum);
		return sum;
	}

	public static void main(String[] args) throws Exception {
		UseCableThread cableThread = new UseCableThread();
		FutureTask<Integer> futureTask = new FutureTask<>(cableThread);
		new Thread(futureTask).start();
		//futureTask.cancel(true); //线程中断
		
	     System.out.println(futureTask.get());//输出线程返回的值
		
	}
}
