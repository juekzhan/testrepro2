package com.xiangxue.ch7.deadlock.zhuan.service;

import com.xiangxue.ch7.deadlock.zhuan.UserAccount;

public interface ITranfer {
	void transfer(UserAccount from, UserAccount to, int amount)
    		throws InterruptedException;
}
