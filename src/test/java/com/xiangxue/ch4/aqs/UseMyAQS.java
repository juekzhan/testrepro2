package com.xiangxue.ch4.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
// 独占锁， 不可重入锁， 递归调用的时候 自己会把自己锁死
public class UseMyAQS implements Lock  {
   
	//同步器类
	static class Sync extends AbstractQueuedSynchronizer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6987779524090357334L;
		// 尝试获取锁 改变state
		@Override
		protected boolean tryAcquire(int arg) {
			//改变成功的话 证明拿到锁
			if(compareAndSetState(0, 1)) {
				//设置线程为当前 为活动线程 并且排斥其他线程
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}
        //尝试释放锁
		@Override
		protected boolean tryRelease(int arg) {
			if(getState() == 0) {
				//throw new Exception();
			}
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}
		// 判断锁的状态 被释放
		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}
		
	}
	//同步器   lock 里面实现的全部交给同步器执行
	private final Sync sync = new Sync();
	
	
	
	@Override
	public void lock() {
     // System.out.println(Thread.currentThread().getName() + "ready get lock");
      sync.acquire(0);
      //System.out.println(Thread.currentThread().getName() +" alreay get lock");
	}
   /**
    * lockInterruptibly()和上面的第一种情况是一样的， 线程在请求lock并被阻塞时，如果被interrupt，则“此线程会被唤醒并被要求处理InterruptedException”。
    */
	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(0);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		  System.out.println(Thread.currentThread().getName() + "ready get unlock");
	      sync.release(1);
	      System.out.println(Thread.currentThread().getName() +" alreay get unlock");
	}

	@Override
	public Condition newCondition() {
		
		return sync.new ConditionObject();
	}
   
}
