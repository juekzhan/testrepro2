package com.xiangxue.cha8b;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.xiangxue.cha8b.assist.Consts;

public class TestCo {
	//处理文档生成的线程池
		private static ExecutorService docMakeService = 
				Executors.newFixedThreadPool(Consts.THREAD_COUNT*2); 
	
		//队列用CompletionSevice   里面就 线程池+ BlockingQueue的合用,谁 先完成先 取谁
		private static CompletionService<String> docCompltionService
		              = new ExecutorCompletionService<>(docMakeService);
		
	public static void main(String[] args) throws Exception {
		
		for(int i= 0; i < 10; i++) {
			Future<String>  f = docCompltionService.submit(new MakeDocTask(i));
			System.out.println(docCompltionService.take().get()  + "   HHH");
			System.out.println(f.get());
		}
		
		
	}
   
	
	
	  /*
	   * 处理文档的线程*/
	  private static class MakeDocTask implements Callable<String>{
	   //  private SrcDocVo srcDocVo;
	     
	     private int i;
//		public MakeDocTask(SrcDocVo srcDocVo) {
//			this.srcDocVo = srcDocVo;
//		}
        
	     
		@Override
		public String call() throws Exception {
	     //   long start = System.currentTimeMillis();
//	        String result = ProduceDocService.makeDoc(srcDocVo);
//			System.out.println("文档"+result +"生成耗时："+(System.currentTimeMillis()-start));
			return i+"";
		}
        public MakeDocTask(int i) {
	     this.i = i;
         }
		  
	  }
	
}
