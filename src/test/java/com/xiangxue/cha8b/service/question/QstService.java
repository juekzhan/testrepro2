package com.xiangxue.cha8b.service.question;



//单个题目处理服务类
public class QstService {
   public static String makeQuestion(Integer questionId,String questionSrc) {
	   //Random r = new Random();
	  // SL_Busi.buisness(20  + r.nextInt());
	   return  "CompleteQuestion[id="+questionId
               +" content=:"+ questionSrc+"]";
   }
}
