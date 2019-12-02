package com.xiangxue.ch2.forkjoin.sort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import com.xiangxue.ch2.forkjoin.MakeArray;


/**
 * 
 * @ClassName: ForkJoinSort  
 * @Package :com.xiangxue.ch2.forkjoin.sort
 * @Description: forkJoin 排序
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年5月5日 下午4:36:38
 */
public class ForkJoinSort extends RecursiveTask<int[]> {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -2325440638471696227L;

	private final static int THRESHOLD =  20;
	
	private int[] src;
	
	public ForkJoinSort(int[] src) {
		this.src = src;
	}



	@Override
	protected int[] compute() {
		if(src.length  <= THRESHOLD) {
			return InsertionSort.sort(src);
		}else {
			 int mid = src.length / 2;
			 ForkJoinSort leftTask = new ForkJoinSort(Arrays.copyOfRange(src, 0, mid));
			 ForkJoinSort rightTask = new ForkJoinSort(Arrays.copyOfRange(src, mid, src.length));
             invokeAll(leftTask,rightTask);
             int[] leftResult = leftTask.join();
             int[] rightResult = rightTask.join();
             return MergeSort.merge(leftResult,rightResult);
		}
	}
   
	public static void main(String[] args) {
		 ForkJoinPool pool = new ForkJoinPool();
	     int[] src = MakeArray.makeArray();
	     
	     ForkJoinSort innerFind = new ForkJoinSort(src);
	     
	     long start = System.currentTimeMillis();
	     pool.invoke(innerFind);
	     System.out.println("innerFind.join()" + innerFind.join() +" spend time:"+(System.currentTimeMillis()-start)+"ms");
	     for(int i : innerFind.join()) {
	    	 System.out.println(i);
	     }
	}
	
}
