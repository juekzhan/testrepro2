package com.xiangxue.thread;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class TestThread {
//	for(int i = 0; i< pageCounts; i++) {
//		//设置查询的起始页，和查询每页的数量
//		param.setPageStartNum(i * OrderConst.PAGING_THRESHOLD);
//		param.setPagingThreshold(OrderConst.PAGING_THRESHOLD);
//	    List<Map<String, Object>> orderExportList = null;
//	    if(mapFields.containsKey(ORDER)) {
//	    	param.setSelectFields(mapFields.get(ORDER));
//	    	orderExportList = torderOrderMapper.getOrderExportList(param);
//	    	resultTotalMap.put(ORDER, orderExportList); //help gc
//	    }else if(mapFields.containsKey(ORDERRECE)) {
//	    	param.setSelectFields(mapFields.get(ORDERRECE));
//	    	orderExportList = torderOrderMapper.getOrderExportReceList(param);
//	    	resultTotalMap.put(ORDERRECE, orderExportList); //help gc
//	    }
//		//如果数据量为阈值的倍数
//		if(orderExportList != null && !orderExportList.isEmpty()) {
//			 orderIds = makeOrderIdList(orderExportList); //help gc
//		}else {
//			return;
//		}
//	}
	//查询数据 起始页为 第1页 ，3个线程 完成
	//ThreadPoolExecutor
	
//	static ExecutorService pool = Executors.newFixedThreadPool(3);
//	
//	public static void main(String[] args) {
//		
//	  
//		
//	}
//	 
//	private void mains() {
//	int pageCounts = 100;
//	for(int i = 0; i< pageCounts; i++) {
//		   //如果有数据，+1   就开启 一个 线程
//		//我就让当前线程等待
//		if(List  > 3000) {
//			ExecutorSer ee = new ExecutorSer(i+1); // 怎么标志这 两个线程已经完成
//			pool.execute(ee);
//		}
//		if() {
//			i += 3;
//		}
//		
//	
//		
//	}
//	//在这个地方我怎么知道我的那两个扩展的线程走完了，加个标志
//	wait();
//	if() {
//		notify();
//	}
//	}
//	
//	
//	public static class ExecutorSer implements Runnable{
//        
//		private int  pageCounts ;
//		
//		public ExecutorSer(int pageCounts) {
//			
//			this.pageCounts = pageCounts;
//		}
//
//
//
//		@Override
//		public void run() {
//			// 查出来的还>3000
//			if(i > 3的倍数) //那就不再去创建线程 
//			//那 就 在 这个地方 再  启动 一个线程
//			ExecutorSer ee = new ExecutorSer(i+1);
//			pool.execute(ee);
//		}
//		
//	}
//	
}
