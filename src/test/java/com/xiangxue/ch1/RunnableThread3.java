package com.xiangxue.ch1;

public class RunnableThread3 {
 private static class RunnableThreadInside implements Runnable{

	@Override
	public void run() {
		System.out.println("This is runnable");
		System.out.println("AAAA");
	}
	 
 }
 
 public static void main(String[] args) {
	new Thread(new RunnableThreadInside()).start();
}
}
