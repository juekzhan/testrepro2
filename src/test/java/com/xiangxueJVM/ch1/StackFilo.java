package com.xiangxueJVM.ch1;
//栈的分析
public class StackFilo {
 public static void main(String[] args) {
	A(); //A()->B()->C()
}
 public static void  A() {     //这个地方入栈     线程单个运行 ，栈帧，设置栈的大小  –Xss 1M(默认) 参数
	 System.out.println("A开始");    //栈一般采用先进后出的原则，因为关联调用
	 //调用
	 B();
	 System.out.println("A结束");
 }                             //这个 地方出栈
 
 public static void B() {
	 System.out.println("B开始");
	 C();
	 System.out.println("B结束");
 }
 
 public static void C() {
	 System.out.println("C开始");
	 System.out.println("C结束");
 }
 
}
