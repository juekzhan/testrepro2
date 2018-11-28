package com.juekjava.threadConcurrency;

public class TestMain2 {

	public static void main(String[] args) {
       Thread1Test thread1Test = new Thread1Test();
       Thread2Test thread2Test = new Thread2Test();
       //thread1Test.setDaemon(true);
       //thread2Test.setDaemon(true);
      // thread1Test.interrupt();
       
       thread1Test.start();
       thread2Test.start();
       try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
       
       System.out.println("主线程"+Thread.currentThread().getName()+"运行完成");
	}

}
