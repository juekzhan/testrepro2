package com.juekjava.designPattern.pipeline;

public class FirstValve implements Valve {
     protected Valve next = null;
     
	@Override
	public Valve getNext() {
		return next;
	}

	@Override
	public void setNext(Valve valve) {
	  this.next = valve;	
	}

	@Override
	public void invoke(String request) {
        System.out.println("定制阀门1处理");	
        getNext().invoke(request);
	}

}
