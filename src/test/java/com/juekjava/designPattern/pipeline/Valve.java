package com.juekjava.designPattern.pipeline;
//管道模式，基础阀门接口
public interface Valve {
	//获取下个阀门
  public Valve getNext();
  
  //设置下一个管道
  public void setNext(Valve valve);
  
  //管道的处理东西
  public void invoke(String handing);
}
