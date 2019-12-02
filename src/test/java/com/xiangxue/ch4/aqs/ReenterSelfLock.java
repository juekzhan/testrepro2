package com.xiangxue.ch4.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 
 * @ClassName: ReenterSelfLock  
 * @Package :com.xiangxue.ch4.aqs
 * @Description: 独占可重入
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年6月24日 上午11:16:34
 */
public class ReenterSelfLock implements Lock {
     
	
	@SuppressWarnings("serial")
	static class Sync extends AbstractQueuedSynchronizer{
       
		
		//是否处于占用状态
		@Override
		protected boolean isHeldExclusively() {
			return getState() > 0;
		}
        
		//当状态为0的时候获取锁  
		//重入就是把同步器的状态位置设置为 大于0的次数
		@Override
		protected boolean tryAcquire(int acquires) {
			if(compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}else if(getExclusiveOwnerThread() == Thread.currentThread()) {
				setState(getState() + 1);
				return true;
			}
			return false;
		}
		
         //释放锁
		@Override
		protected boolean tryRelease(int arg) {
			if(getExclusiveOwnerThread()  != Thread.currentThread()) {
				throw new IllegalArgumentException();
			}
			if(getState() == 0) {
				throw new IllegalArgumentException();
			}
			setState(getState() -1);
			if(getState() == 0) {
				setExclusiveOwnerThread(null);
			}
			return true;
		}
		
		 // 返回一个Condition，每个condition都包含了一个condition队列
        Condition newCondition() {
            return new ConditionObject();
        }
	}
	
	private final Sync sync = new Sync();
	
	@Override
	public void lock() {
		
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
		
	}

	@Override
	public boolean tryLock() {
		
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(1));
	}

	@Override
	public void unlock() {
		System.out.println(Thread.currentThread().getName()+" ready release lock");
        sync.release(1);
        System.out.println(Thread.currentThread().getName()+" already released lock");
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

}
