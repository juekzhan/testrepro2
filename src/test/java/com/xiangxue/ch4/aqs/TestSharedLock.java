package com.xiangxue.ch4.aqs;

public class TestSharedLock {
	public void test() throws InterruptedException {
	  final UseSharedLockAQS shareLock = new UseSharedLockAQS();
	  
	  class Woker extends Thread{

		@Override
		public void run() {
		  shareLock.lock();
		  System.out.println(Thread.currentThread().getName());
		  try {
			  Thread.sleep(2000);
		  } catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
    	    shareLock.unlock();
		  }
		}
	  }
	  
	  
	for(int i = 0 ;i< 9;i++) {
	  new Woker().start();		
	 }
     
	 for(int j = 0 ;j< 10;j++) {
		 Thread.sleep(200);
	 }
	
	}
	
  public static void main(String[] args) throws InterruptedException {
	  TestSharedLock trs = new TestSharedLock();
	  trs.test();
  }
}
