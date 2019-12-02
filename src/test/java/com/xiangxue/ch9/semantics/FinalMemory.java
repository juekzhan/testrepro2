package com.xiangxue.ch9.semantics;

//类说明：final 内存语义
public class FinalMemory {
	int i;
	final int j;
	static FinalMemory obj;

	public FinalMemory() {
		this.i = 1; // 写普通域
		this.j = 2; // 写final域
	}

	public static void writer() { // 写线程A执行
		obj = new FinalMemory();
	}

	@SuppressWarnings("unused")
	public static void reader() { // 读线程B执行
		FinalMemory object = obj;
		int a = obj.i;
		int b = obj.j;
	}
}
