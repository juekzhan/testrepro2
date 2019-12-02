package com.xiangxue.cha8b.vo;

/*
 * 题目保存在缓存中的实体
 * */
public class QuestionInCacheVo {
	private final String questionDetail;

	private final String questionSha;

	public QuestionInCacheVo(String questionDetail, String questionSha) {
		this.questionDetail = questionDetail;
		this.questionSha = questionSha;
	}

	/**
	 * @return the questionDetail
	 */
	public String getQuestionDetail() {
		return questionDetail;
	}

	/**
	 * @return the questionSha
	 */
	public String getQuestionSha() {
		return questionSha;
	}

}
