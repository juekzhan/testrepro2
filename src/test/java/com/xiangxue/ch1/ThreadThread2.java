package com.xiangxue.ch1;

public class ThreadThread2  {
  private static class ThreadInsideThread extends Thread{
	  public void run() {
		  System.out.println("this is Thread");
	  }
  }
  public static void main(String[] args) {
	ThreadInsideThread thread = new ThreadInsideThread();
	thread.start();
	
}
}
