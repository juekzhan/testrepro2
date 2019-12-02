package com.xiangxue.ch4;
/**
 * 
 * @ClassName: UseLockReentrantLock  
 * @Package :com.xiangxue.ch4
 * @Description: 重入锁的的实例（重入也包括syn 关键字  就是在递归调用的时候不至于拿不到锁 卡死）
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年5月10日 上午10:36:07
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseLockReentrantLock {
     //独占锁，悲观锁 可重入锁
	private Lock lock = new ReentrantLock(); //可重入锁就是递归调用的时候，照样用的一把锁（不会自己把自己锁死）  //默认为fasle  为非公平锁
	//线程工具类  ，用里面的 阻止 和唤醒线程
	//private LockSupport lockSupport;
	//private Lock lockFair = new ReentrantLock(true);  // 为true 为公平锁
	
	private int age = 0;
	
	private static class TestThread implements Runnable{
         private UseLockReentrantLock useLock;
         
         
		
		public TestThread(UseLockReentrantLock useLock) {
			super();
			this.useLock = useLock;
		}



		@Override
		public void run() {
			
				useLock.addAge();
				//System.out.println(Thread.currentThread().getName() + ":" + useLock.getAge() +"xxx");
		}
		
	}
	
	public void addAge() {
		lock.lock();   //获得锁 这东西
		try {
			age ++ ;
			System.out.println(Thread.currentThread().getName() + ":" + age + "Thread safe");
		}finally {
			lock.unlock();  //释放锁   都要释放
		}
	}
	
	public void jianAge() {
		  lock.lock();
		try {
			age --;
		}finally {
			lock.unlock();   // 解锁的范式  出现异常也把锁给释放掉
		}
	}
	
	public int getAge() {
		return age;
	}
	
	public static void main(String[] args) {
		UseLockReentrantLock lockReentrantLock = new UseLockReentrantLock();
		for(int i = 0 ;i < 100; i++) {
			new Thread(new TestThread(lockReentrantLock)).start();
		}
	}
}
