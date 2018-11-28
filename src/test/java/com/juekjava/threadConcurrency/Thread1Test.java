package com.juekjava.threadConcurrency;

public class Thread1Test extends Thread {
    private int dispValue = 2;
	@Override
	public void run() {
		int cycleIndex = 10;
		while(cycleIndex > 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cycleIndex--;
			System.out.println("线程"+Thread.currentThread().getName()+"累加输出的值"+dispValue ++);
		}
	}
  
}
