package com.xiangxueJVM.ch6;


//逃逸分析
//小对象在栈 上分配  ,jvm 默认是开启 逃逸分析 
//-XX:+DoEscapeAnalysis：启用逃逸分析(默认打开)
//-XX:+EliminateAllocations：标量替换(默认打开) 
//-XX:+UseTLAB 本地线程分配缓冲(默认打开) 
public class StackAlloc {
  public static class User{
	  public int id =0;
	  public String name = "";
  }
  
  public static void alloc() {
	  User u = new User(); //Object  在堆上分配的() ,有逃逸分析的技术 ，在栈中分配的
	  u.id = 5;
	  u.name = "juek";
  }
  
  public static void main(String[] args) {
	long b = System.currentTimeMillis();
	for(int i=0;i<100000000;i++) {//一个方法运行1亿次（）
		alloc();
	}
	long e = System.currentTimeMillis(); //结束时间
	System.out.println(e-b);//打印运行时间：毫秒
}
}
