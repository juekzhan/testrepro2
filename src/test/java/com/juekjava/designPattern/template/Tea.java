package com.juekjava.designPattern.template;


/**
 * 茶类
 * @author zsh12489
 *
 */
public class Tea extends CaffeeineBeverage {

	@Override
	void brew() {
		System.out.println("Steeping the Tea");
	}

	@Override
	void addCondiments() {
	  System.out.println("Adding Lemon");
	}
	
}
