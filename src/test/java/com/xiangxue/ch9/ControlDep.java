package com.xiangxue.ch9;
//控制依赖
public class ControlDep {
  int a = 0;
  volatile boolean flag  =  false;
  
  public void init() {
	  a = 1;
	  flag = true;
  }
  
  @SuppressWarnings("unused")
public synchronized  void  use() {
	  if(flag) {
		  int i = a*a;
	  }
  }
  
}
