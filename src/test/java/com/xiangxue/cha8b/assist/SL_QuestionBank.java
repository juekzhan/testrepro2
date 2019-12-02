package com.xiangxue.cha8b.assist;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.xiangxue.cha8b.vo.QuestionInDBVo;

public class SL_QuestionBank {
  //题库数据 存储
	private static ConcurrentHashMap<Integer, QuestionInDBVo> questionBankMap
	                 = new ConcurrentHashMap<>();
	//定时任务池 ，负责定时更新题库中的数据
	private static ScheduledExecutorService updateQuestionBank 
	               = new ScheduledThreadPoolExecutor(1);
	
	//初始化题库
	public static void initBank() {
		for(int i=0;i<Consts.SIZE_OF_QUESTION_BANK;i++) {
			String questionContent =  makeQuestionDetail(100);
			questionBankMap.put(i, new QuestionInDBVo(i, questionContent,
					EncryptUtils.EncryptBySHA1(questionContent)));
		}
		updateQuestionTimer();
	}
	
	//生成随机字符串，代表题目实际内容，length表示题目长度
	private static String makeQuestionDetail(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i =0 ;i<length ; i ++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	//获得题目，我们假设一次性数据库的读耗时间再20ms左右，所以休眠20ms
	public static QuestionInDBVo getQuestion(int i) {
		SL_Busi.buisness(20);
		return questionBankMap.get(i);
	}
	
	//更新题库 的定时任务
	private static class UpdateBank implements Runnable{
      
		@Override
		public void run() {
         Random random = new Random();
         int questionId = random.nextInt(Consts.SIZE_OF_QUESTION_BANK);
         String questionContent = makeQuestionDetail(700);
         questionBankMap.put(questionId, new QuestionInDBVo(questionId,
        		 questionContent, EncryptUtils.EncryptBySHA1(questionContent)));
		}
	}
	
	//定时更新数据库数据
	private static void updateQuestionTimer() {
		System.out.println("开始 定时更新数据库.....................");
		updateQuestionBank.scheduleAtFixedRate(new UpdateBank(), 15, 5, TimeUnit.SECONDS);
	}
	
	//获得题目的Sa值，我们假设一次数据库的读 耗时一般在 10ms左右 ，所以休眠10ms
	public static String getQuestionSha(int i) {
		SL_Busi.buisness(20);
		return questionBankMap.get(i).getSha();
	}
}
