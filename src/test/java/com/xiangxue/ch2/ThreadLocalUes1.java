package com.xiangxue.ch2;

public class ThreadLocalUes1  {
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 1;
		}
	};
	
  public static class ThreadLocalInside extends Thread{
	  private int id;
	  public ThreadLocalInside(int id) {
		this.id = id;
	}
	  
	  public void run() {
		   Integer i = threadLocal.get();
		   i  = i +id ;
		   threadLocal.set(i);
		   System.out.println("this is num = " + threadLocal.get());
	   }
  }
  
  public static void main(String[] args) {
	for(int i = 0; i< 5; i++) {
		new ThreadLocalInside(i).start();
	}
}
}
