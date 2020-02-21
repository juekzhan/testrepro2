package com.xiangxue.ch2.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import com.xiangxue.ch1.SleepTools;

/**
 * 
 * @ClassName: SumTask  
 * @Package :com.xiangxue.ch2.forkjoin
 * @Description: fork/join 的用法  RecursiveTask<Integer> 有返回值的
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 */
public class SumTask extends RecursiveTask<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 157482140417520372L;
	//阈值
    private final static int THRESHOLD = MakeArray.ARRAY_LENGTH/10;
    private int[] src;
    private int fromIndex;
    private int toIndex;
    
    
	public SumTask(int[] src, int fromIndex, int toIndex) {
		this.src = src;
		this.fromIndex = fromIndex;
		this.toIndex = toIndex;
	}



	public SumTask(int[] copyOfRange) {
		
	}



	/*
	 * (non-Javadoc)
	 * @see java.util.concurrent.RecursiveTask#compute()
	 * 拆分任务的方法 ，返回值
	 */
	@Override
	protected Integer compute() {
		//计算现在的值是否小于当前的值
		if(toIndex - fromIndex < THRESHOLD) {
			//System.out.println(" fromIndex = " + fromIndex +"toIndex =" + toIndex);
			int count = 0;
			for(int i = fromIndex ; i<= toIndex; i++) {
				SleepTools.secondS(1);
				count = count + src[i];
			}
			return count;
		}else {
			int mid = (fromIndex + toIndex)/2;
			//以下是递归调用
			SumTask left = new SumTask(src, fromIndex, mid);
			SumTask right = new SumTask(src, mid+1, toIndex);
			invokeAll(left,right);  //归并再调用
			return left.join() + right.join();//最后的值返回
		}
	}
   
	
	public static void main(String[] args) {
		int[] src = MakeArray.makeArray();
		//用fork/join 先拿到fork/join的一个链接池
		ForkJoinPool pool = new ForkJoinPool();
		SumTask innerFind = new SumTask(src, 0, src.length-1);
		
		long start = System.currentTimeMillis();
		//同步返回值
		pool.invoke(innerFind);
		
		System.out.println("The count is "+ innerFind.join() + " spend time :"+(System.currentTimeMillis()-start)+"ms");
	}
}
