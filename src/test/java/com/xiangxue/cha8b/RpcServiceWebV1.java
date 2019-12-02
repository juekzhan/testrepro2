package com.xiangxue.cha8b;
 //服务化 ，异步化

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.xiangxue.cha8b.assist.Consts;
import com.xiangxue.cha8b.assist.CreatePendingDocs;
import com.xiangxue.cha8b.assist.SL_QuestionBank;
import com.xiangxue.cha8b.service.ProduceDocService;
import com.xiangxue.cha8b.vo.SrcDocVo;

public class RpcServiceWebV1 {
  //定义一个线程池 处理生成doc
	private static ExecutorService docExecutorService =
			   Executors.newFixedThreadPool(Consts.THREAD_COUNT*2);
  //定义一个上传的线程池 处理上传
	private static ExecutorService docUploadExecutorService =
			       Executors.newFixedThreadPool(Consts.THREAD_COUNT*2);
	//定义个完成的队列，包含线程池，和BlokingQueue ，先完成的先返回数值
	private static CompletionService<String> docCompltionService = 
			         new ExecutorCompletionService<>(docExecutorService);
	private static CompletionService<String> docUpCompltionService =
			        new ExecutorCompletionService<>(docUploadExecutorService);
	
	public static void main(String[] args) throws Exception {
		int docCount = 60;
		System.out.println("题库开始初始化......");
		SL_QuestionBank.initBank();
		System.out.println("题库初始化完成.......");
	    
		List<SrcDocVo> docList = CreatePendingDocs.makePendingDoc(docCount);   //生成待处理的文档
		long startTotal = System.currentTimeMillis();
		for(SrcDocVo srcDocVo : docList) {
			docCompltionService.submit(new MakeDocTask(srcDocVo));
		}
		//上传
		for(int i =0 ; i<docCount ;i++) {
			Future<String> future = docCompltionService.take();
			docUpCompltionService.submit(new UploadTask(future.get()));
		}
		//展示时间
		for(int i =0 ; i<docCount ;i++) {
			docUpCompltionService.take().get();
		}
		  System.out.println("共耗时："+(System.currentTimeMillis()-startTotal)+"ms");
	}
	//生成文档的线程
	private static class MakeDocTask implements  Callable<String>{
        private SrcDocVo SrcDocVo;
		public MakeDocTask(SrcDocVo srcDocVo) {
			SrcDocVo = srcDocVo;
		}

		@Override
		public String call() throws Exception {
			 long start = System.currentTimeMillis();
			 String result = ProduceDocService.makeDoc(SrcDocVo);
			 System.out.println("文档"+result+"生成耗时："
	                    +(System.currentTimeMillis()-start)+"ms");
			 return result;
		}
	}
	
	//处理上传的线程
	private static class UploadTask implements Callable<String>{
        private String fileName;
        
		public UploadTask(String fileName) {
			this.fileName = fileName;
		}

		@Override
		public String call() throws Exception {
			 long start = System.currentTimeMillis();
			 String result = ProduceDocService.upLoadDoc(fileName);
			 System.out.println("已上传至["+result+"]耗时："
	                    +(System.currentTimeMillis()-start)+"ms");
			return result;
		}
		
	}
}
