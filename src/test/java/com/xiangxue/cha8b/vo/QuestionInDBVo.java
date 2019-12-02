package com.xiangxue.cha8b.vo;

public class QuestionInDBVo {
	// 题目id
	private final int id;
	// 题目详情
	private final String detail;
	// 题目摘要
	private final String sha;

	public QuestionInDBVo(int id, String detail, String sha) {
		this.id = id;
		this.detail = detail;
		this.sha = sha;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @return the sha
	 */
	public String getSha() {
		return sha;
	}
   
	
}
