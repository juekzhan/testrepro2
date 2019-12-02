package com.xiangxue.cha8b.service.question;

import com.xiangxue.cha8b.assist.SL_QuestionBank;

//类说明：模拟解析题目文本，下载图片等操作，返回解析后的文本
public class SingleQstService {
 public static String makeQuestion(Integer qInteger) {
	 return QstService.makeQuestion(qInteger
         , SL_QuestionBank.getQuestion(qInteger).getDetail());
 }
}
