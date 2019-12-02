package com.xiangxue.ch7.deadlock.zhuan.service;

import com.xiangxue.ch7.deadlock.zhuan.UserAccount;

public class TrasnferAccount implements ITranfer {

	@Override
	public void transfer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
		synchronized (from) {
			System.out.println(Thread.currentThread().getName()  +" get " + from.getName());
			synchronized (to) {
			  System.out.println(Thread.currentThread().getName() +" get " + to.getName());
			  from.flyMoney(amount);
			  to.addMoney(amount);
			}
		}
		
	}

}
