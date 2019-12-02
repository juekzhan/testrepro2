package com.xiangxue.ch4.readwritelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UseReadWriteLock implements GoodsService {
   
	//前面是接口 ，里面分为读锁，和写锁
	private ReadWriteLock readAndWriteLock = new ReentrantReadWriteLock();
	
	//读锁 （ 读共享  ，读写互斥 ，写锁被拿  不管是读锁，还是写锁，都拿不到）   当线程持有读锁 的时候， 写线程 想拿写锁 ，拿不到
	private Lock readLock = readAndWriteLock.readLock();    //读不排斥读，排斥写
	//写锁
	private Lock writeLock = readAndWriteLock.writeLock();   //写排斥 读也排斥写
	
	
	private GoodsInfo goodsInfo;
	
	public UseReadWriteLock(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	@Override
	public GoodsInfo getNum() {
	    readLock.lock();
		try {
	     return goodsInfo;
	    }finally {
	    	readLock.unlock();
	    }
	}

	@Override
	public void setNum(int number) {
		writeLock.lock();
		try {
			goodsInfo.changeNumber(number);
		}finally {
			writeLock.unlock();
		}
	}

}
