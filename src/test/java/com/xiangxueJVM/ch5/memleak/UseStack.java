package com.xiangxueJVM.ch5.memleak;
//内存泄露
public class UseStack {
	 public static void main(String[] args) {
	        MyStack stack = new MyStack();  //new一个栈
	        Object o = new Object(); //new一个对象
	        System.out.println("o="+o);
	        stack.push(o); //入栈
	        Object o1 =  stack.pop(); //出栈
	        //o对象没什么用
	        System.out.println("o1="+o1);

	        System.out.println(stack.elements[0]); //打印栈中的数据

	    }

}
