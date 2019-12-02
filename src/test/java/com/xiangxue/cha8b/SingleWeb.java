package com.xiangxue.cha8b;


import java.util.List;

import com.xiangxue.cha8b.assist.CreatePendingDocs;
import com.xiangxue.cha8b.assist.SL_QuestionBank;
import com.xiangxue.cha8b.service.ProduceDocService;
import com.xiangxue.cha8b.vo.SrcDocVo;

//类说明
public class SingleWeb {
   public static void main(String[] args) {
	System.out.println("题库开始初始化......");
	SL_QuestionBank.initBank();
	System.out.println("题库初始化完成.......");
	
	List<SrcDocVo> docList = CreatePendingDocs.makePendingDoc(60);
	long startTotal = System.currentTimeMillis();
	for(SrcDocVo doc : docList) {
		System.out.println("["+doc.getDocName()+"]共有题目："+doc.getQuestionList().size()+"个");
		System.out.println("开始处理文档："+doc.getDocName()+".......");
		long start = System.currentTimeMillis();
		String localName = ProduceDocService.makeDoc(doc);
		System.out.println("文档"+localName+"生成耗时："+(System.currentTimeMillis()-start)+"ms");
		start = System.currentTimeMillis();
		String remoteUrl = ProduceDocService.upLoadDoc(localName);
		System.out.println("已经上传至["+remoteUrl+"]耗时："+(System.currentTimeMillis()-start)+"ms");
	}
	System.out.println("共耗时："+(System.currentTimeMillis()-startTotal)+"ms");
  }
   
  
}
