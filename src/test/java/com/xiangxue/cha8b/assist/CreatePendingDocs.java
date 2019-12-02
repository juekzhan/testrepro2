package com.xiangxue.cha8b.assist;
 //生成待处理的文档

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.xiangxue.cha8b.vo.SrcDocVo;

public class CreatePendingDocs {
 public static List<SrcDocVo> makePendingDoc(int count){
	 Random  r = new Random();
	// Random rCount = new Random();
	 
	 List<SrcDocVo> docList = new LinkedList<SrcDocVo>();
	 for(int i =0 ;i<count;i++) {
		 List<Integer> questionList = new LinkedList<>(); //文档中题目列表
		 int questNum = r.nextInt(60)  + Consts.QUESTION_COUNT_IN_DOC;
		 for(int j = 0 ;j< questNum; j++) {
			 int questionId = r.nextInt(Consts.SIZE_OF_QUESTION_BANK);
			 questionList.add(questionId);
		 }
		 SrcDocVo pendingDocVo = new SrcDocVo("pending_"+i, questionList);
		 docList.add(pendingDocVo);
	 }
	 return docList;
 }
}
