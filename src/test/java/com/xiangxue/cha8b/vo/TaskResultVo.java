package com.xiangxue.cha8b.vo;
/**
 * 
 * @ClassName: TaskResultVo  
 * @Package :com.xiangxue.cha8b.vo
 * @Description: 并发处理的结果类
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月31日 下午2:11:35
 */

import java.util.concurrent.Future;

public class TaskResultVo {
	private final String questionDetail;

	private final Future<QuestionInCacheVo> questionFuture;

	public TaskResultVo(String questionDetail) {
		this.questionDetail = questionDetail;
		this.questionFuture = null;
	}

	public TaskResultVo(Future<QuestionInCacheVo> questionFuture) {
		this.questionDetail = null;
		this.questionFuture = questionFuture;
	}

	/**
	 * @return the questionDetail
	 */
	public String getQuestionDetail() {
		return questionDetail;
	}

	/**
	 * @return the questionFuture
	 */
	public Future<QuestionInCacheVo> getQuestionFuture() {
		return questionFuture;
	}

}
