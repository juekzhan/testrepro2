package com.juekjava.designPattern.pipeline;

//管道基础类
public class StandarValve implements Valve {
    
	protected Valve next = null;
	@Override
	public Valve getNext() {
		return next;
	}
	@Override
	public void setNext(Valve valve) {
		this.next = next;
		
	}
	@Override
	public void invoke(String request) {
		request = request + "xxoo";
		System.out.println("基础阀门处理");
	}

}
