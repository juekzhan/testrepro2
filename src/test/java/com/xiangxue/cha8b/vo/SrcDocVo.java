package com.xiangxue.cha8b.vo;

import java.util.List;

/**
 * 
 * @ClassName: SrcDocVo  
 * @Package :com.xiangxue.cha8b.vo
 * @Description: 待处理文档实体类
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月31日 下午2:01:01
 */
public class SrcDocVo {
  //待处理文档名称
	private final  String docName;
	//待处理文档中题目id列表
	private final List<Integer> questionList;
	
	
	public SrcDocVo(String docName, List<Integer> questionList) {
		super();
		this.docName = docName;
		this.questionList = questionList;
	}
	/**
	 * @return the docName
	 */
	public String getDocName() {
		return docName;
	}
	/**
	 * @return the questionList
	 */
	public List<Integer> getQuestionList() {
		return questionList;
	}
	
	
}
