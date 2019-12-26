package com.juekjava.designPattern.observer;

public class Test {

	public static void main(String[] args) {
	  Subject returnOrder = new SubjectImpl();
	  
	  SubChild order1 = new Order();
	  SubChild order2 = new Order();
	  SubChild order3 = new Order();
	  SubChild order4 = new Order();
	  SubChild order5 = new Order();
	  
	  returnOrder.addWath(order1);
	  returnOrder.addWath(order2);
	  returnOrder.addWath(order3);
	  returnOrder.addWath(order4);
	  returnOrder.addWath(order5);
	  
	  returnOrder.tongZhi("已完成");

	}

}
