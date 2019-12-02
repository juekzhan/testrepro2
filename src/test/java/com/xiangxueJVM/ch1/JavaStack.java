package com.xiangxueJVM.ch1;
//虚拟机栈
public class JavaStack {
 //常量
	final String Fs = "骚骚明 ，又骚又光明"; //放在方法区域内部
	//静态变量
	static String Ss = "以静制动"; //也是放在方法区域 的内部 ，因为是公用的
	
	int  count  = 0; //放在堆中
	public void king(int money) {    //放在对应的栈中
		money = money - 100;
		System.out.println(money);     
		//找到对应class 文件进行 javap -v JavaStack.class > aa.txt 编译出来 的指令 ，C++
		//对应的栈信息
		// public void king(int);
//	    descriptor: (I)V
//	    flags: ACC_PUBLIC
//	    Code:
//	      stack=2, locals=2, args_size=2
//	         0: iinc          1, -100         // 操作指令的信息
//	         3: getstatic     #33                 // Field java/lang/System.out:Ljava/io/PrintStream;
//	         6: iload_1
//	         7: invokevirtual #39                 // Method java/io/PrintStream.println:(I)V
//	        10: return
//	      LineNumberTable:
//	        line 11: 0                  //计数器记录的信息
//	        line 12: 3
//	        line 13: 10
//	      LocalVariableTable:
//	        Start  Length  Slot  Name   Signature  
//	            0      11     0  this   Lcom/xiangxueJVM/ch1/JavaStack;     //变量的信息
//	            0      11     1  money   I                                 //变量的信息
	}
	
	public static void main(String[] args) {
		JavaStack javaStack = new JavaStack();
		javaStack.king(1000);
	}
	
	
	//几乎所有对象都分配在这里，也是垃圾回收发生的主要区域，可用以下参数调整：
	//-Xms：堆的最小值；
	//-Xmx：堆的最大值；
	//-Xmn：新生代的大小；
	//-XX:NewSize；新生代最小值；
	//-XX:MaxNewSize：新生代最大值；
	//例如- Xmx256m
}
