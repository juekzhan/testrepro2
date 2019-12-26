package com.juekjava.designPattern.observer;

public interface Subject {
 //增加一个观察者
	public void addWath(SubChild obj);
	
	public void delWath(SubChild obj);
	
	public void tongZhi(String stauts);
}
