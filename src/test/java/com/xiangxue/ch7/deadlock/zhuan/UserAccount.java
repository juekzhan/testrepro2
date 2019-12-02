package com.xiangxue.ch7.deadlock.zhuan;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserAccount {
	private final String name; // 账户名称

	private int money;

	private final Lock lock = new ReentrantLock();   //内部加锁

	public Lock getLock() {
		return lock;
	}

	public UserAccount(String name, int money) {
		this.name = name;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public int getMoney() {
		return money;
	}

	@Override
	public String toString() {

		return "UserAccount{" + "name= " + name + "  money=" + money + "}";
	}

//转入资金
	public void addMoney(int amountIn) {
		money += amountIn;
	}

	// 转出资金
	public void flyMoney(int amountIn) {
		money -= amountIn;
	}

}
