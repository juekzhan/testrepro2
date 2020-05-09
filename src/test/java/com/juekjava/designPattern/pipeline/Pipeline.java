package com.juekjava.designPattern.pipeline;

public interface Pipeline {
   //获取 第一个阀门
	public Valve getFirst();
	//获取基础阀门
	public Valve getBasic();
	//设置阀门
	public void setBasic(Valve valve);
	//添加阀门
	public void addValve(Valve valve);
}
