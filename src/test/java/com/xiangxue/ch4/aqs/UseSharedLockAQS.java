package com.xiangxue.ch4.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//共享锁 3元共享
public class UseSharedLockAQS implements Lock {
    
	//为你表示允许n个线程同时获得锁
	private final Sync sync = new Sync(4);
	
	//同步器  （可以同时几个线程获得锁）
	@SuppressWarnings("serial")
	private static final class Sync extends AbstractQueuedSynchronizer {
	    
		Sync(int count){
	    	if(count <= 0) {
	    		throw new IllegalArgumentException("count must large than zero.");
	    	}
	    	setState(count);   //表示同时可以有count个线程同时访问
	    }
        
		 /**
		  * 死循环 获取锁   获取到锁了代表newCount的	   
		  * reduceCount  代表扣减个数
		  * 返回小于0 ， 已经扣减完链表 ，表示同步当前 线程 失败，
		  * 大于0表示获取成功  还有3个可以获取 后面自选锁获得成功
		  */
		@Override
		protected int tryAcquireShared(int reduceCount) {
			for(;;) {
				int current = getState();
				int newCount  = getState() - reduceCount;
				if(newCount < 0 ||  compareAndSetState(current,newCount)) {
					return newCount;
				}
			}
		}
        
		/**
		 * 归还锁  returnCount 个数
		 * 
		 */
		@Override
		protected boolean tryReleaseShared(int returnCount) {
			for(;;) {
				int current = getState();
				int newCount = current + returnCount;
				if(compareAndSetState(current, newCount)) {
					return true;
				}
			}
		}
	    
	     final Condition newCondition() {
	    	 return new ConditionObject();
	     }
		
	    
	}
	
	
	@Override
	public void lock() {
		//调用模板方法
		sync.acquireShared(1);
		
	}
    
	@Override
	public void unlock() {
		sync.releaseShared(1);
	}
	
	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireSharedInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		 return sync.tryAcquireShared(1) >= 0;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		 return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition() ;
	}
  
	
	
}
