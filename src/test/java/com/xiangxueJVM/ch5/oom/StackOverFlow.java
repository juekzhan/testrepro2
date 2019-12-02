package com.xiangxueJVM.ch5.oom;
//栈溢出 设置栈的大小 为 -xss1m 为1M
// java.lang.StackOverflowError
//1M *5000线程同时在跑 =5G 内存小于5G   申请不到内存，也会抛出内存异常 OutOfMerry
public class StackOverFlow {
	 public void king(){
	        king();//死递归
	     }
	    public static void main(String[] args)throws Throwable {
	        StackOverFlow javaStack = new StackOverFlow();
	        javaStack.king();
	    }
}
