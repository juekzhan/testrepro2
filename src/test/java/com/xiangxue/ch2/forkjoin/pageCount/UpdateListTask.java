package com.xiangxue.ch2.forkjoin.pageCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


public class UpdateListTask extends RecursiveAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8897060428010121168L;

	//
	
	
	private Map<String,Integer> mapPageNums = new ConcurrentHashMap<>();
	
	private static final Integer PAGESIZE = 1000;
	
	public UpdateListTask(Map<String,Integer> mapPageNums ) {
		this.mapPageNums = mapPageNums;
	}
	
	
	@Override
	protected void compute() {
		List<UpdateListTask> subTasks = new ArrayList<>();
		//遍历map中的值
		if(isNotNullMap()) {
			//得到相应的map
			Map<String,Integer> startNumsMap = addMapStartNum();
			dispMap(startNumsMap);
			Map<String,Integer> thismapPageNums = subtractionMapValue();
			UpdateListTask task = new UpdateListTask(thismapPageNums);
			subTasks.add(task);
		}
		
		if(!subTasks.isEmpty()) {
			for(UpdateListTask task : invokeAll(subTasks)) {
				task.join();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			ForkJoinPool pool = new ForkJoinPool();
			Map<String,Integer> mapPageNums = new ConcurrentHashMap<>();
			mapPageNums.put("order", 500);
			mapPageNums.put("orderLog", 400);
			mapPageNums.put("orderActive", 600);
			
			
			UpdateListTask task = new UpdateListTask(mapPageNums);
			//同步提交
			pool.invoke(task);
			System.out.println("Task is Running .......");
			Thread.sleep(1);

			task.join();//阻塞方法
			System.out.println("Task End");
		}catch (Exception e) {
          e.printStackTrace();
		}
	}
     
	
	private boolean isNotNullMap(){
		for(Map.Entry<String, Integer> m :mapPageNums.entrySet())  {  
			  if(m.getValue() >= 1) {
				  return true;
			  }
		} 
		return false;
	}
	
	
	private Map<String,Integer> subtractionMapValue() {
		Map<String,Integer> mapPageNumsLocal = new ConcurrentHashMap<>();
		for(Map.Entry<String, Integer> m :mapPageNums.entrySet())  {
			if(m.getValue() >= 1 ) {
				mapPageNumsLocal.put(m.getKey(), m.getValue() - 1);
			}
		}
		return mapPageNumsLocal;
	}
	
	
	private Map<String,Integer> addMapStartNum(){
		Map<String,Integer> startNumsMap = new ConcurrentHashMap<>();
		for(Map.Entry<String, Integer> m :mapPageNums.entrySet())  {
			if(m.getValue() >= 1 ) {
				startNumsMap.put(m.getKey(), (m.getValue() - 1) * PAGESIZE);
			}
		}
		return startNumsMap;
	}
	
	private void dispMap(Map<String,Integer> startNumsMap) {
		for(Map.Entry<String, Integer> m :startNumsMap.entrySet())  {
			System.out.print(m.getKey()+"="+m.getValue() + "\t");  
		}
        System.out.println("");
		System.out.println("=====================");
	}
}
