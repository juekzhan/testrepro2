package com.juekjava.threadConcurrency;

public class Thread2Test extends Thread {
    private int dispValue = 2;
	@Override
	public void run() {
		int cycleIndex = 10;
		while(cycleIndex > 0) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cycleIndex--;
			System.out.println("线程"+Thread.currentThread().getName()+"累加乘以的值"+ (dispValue=dispValue*2));
		}
	}
  
}
