package com.xiangxue.cha8b.assist;

public interface Consts {
    //线程数大小
	public final static int THREAD_COUNT
	= Runtime.getRuntime().availableProcessors();
    
	//每个文档中题目的个数
	public static final int QUESTION_COUNT_IN_DOC = 600;
	
	//题库大小
	public static final int SIZE_OF_QUESTION_BANK = 50000;
}
