package com.juekjava.designPattern.proxy;

/**
 * 糖果监视器
 * 
 * @author zsh12489
 *
 */
public class GumballMonitor {
	GumballMachine machine;

	public GumballMonitor(GumballMachine machine) {
		super();
		this.machine = machine;
	}
    
	
	public void report() {
		System.out.println("Gumball Machine :"+ machine.getLocation());
		System.out.println("Current inventory :"+ machine.getCount());
		System.out.println("Gumball state :"+ machine.getState());
	}
}
