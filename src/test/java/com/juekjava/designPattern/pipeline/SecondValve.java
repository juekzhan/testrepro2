package com.juekjava.designPattern.pipeline;

public class SecondValve implements Valve {
   
	 protected  Valve next =null;
	
	@Override
	public Valve getNext() {
		  return next;
	}

	@Override
	public void setNext(Valve valve) {
		  this.next =valve;
		
	}

	@Override
	public void invoke(String request) {
		   System.out.println("第二个定制阀门处理!");
	       getNext().invoke(request);
	}

}
