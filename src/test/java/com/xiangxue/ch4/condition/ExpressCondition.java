package com.xiangxue.ch4.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.xiangxue.ch1.SleepTools;

public class ExpressCondition {
	public final static String  CITY = "shanghai";

	private int km;/* 快递运输里程数 */
	private String site;/* 快递到达地点 */
	
	private Lock lock = new ReentrantLock();
	private Condition kmCondition  = lock.newCondition();
	private Condition siteCondition = lock.newCondition();
	
	public ExpressCondition(int km, String site) {
		this.km = km;
		this.site = site;
	}
	public ExpressCondition() {
	}
	
	public void changeKm() {
		lock.lock();
		try {
		 this.km = 101;
		 //notifyAll(); //唤醒所有等待的线程， notify 只唤醒一个等待的线程  并且这个线程未知
		 kmCondition.signalAll();
		}finally {
		  lock.unlock();	
		}
	}
	
	public  void changeSite() {
		lock.lock();
		try {
			this.site = "北京";
			//notifyAll();
			siteCondition.signalAll();
		}finally {
			lock.unlock();
		}
		
	}
	
	public   void waitKm() {
		lock.lock();
		try {
		while(km <= 100) {
			try {
				System.out.println(Thread.currentThread().getName()+" KM is wait");
				kmCondition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
		}finally {
			lock.unlock();
		}
		System.out.println(Thread.currentThread().getName()+"  KM is running.....");
		SleepTools.second(2);
		System.out.println(Thread.currentThread().getName()+" KM is over.....");
	}
	
	public  void waitSite() {
		lock.lock();
		try {
			while(CITY.equals(site)) {
				try {
					System.out.println(Thread.currentThread().getName()+"Site is wait");
					siteCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}finally {
			lock.unlock();
		}
	
		System.out.println(Thread.currentThread().getName()+"Site is running.....");
		SleepTools.second(2);
		System.out.println(Thread.currentThread().getName()+"Site is over.....");
	}
}
