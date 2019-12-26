package com.juekjava.designPattern.observer;

public class Order implements SubChild {

	@Override
	public void update(String stauts) {
		System.out.println("状态更新为:"+stauts+"xx");
	}

}
