package com.xiangxue.ch7.deadlock.zhuan.service;

import java.util.Random;

import com.xiangxue.ch7.deadlock.zhuan.UserAccount;

public class SafeTryLock implements ITranfer {
   
	@Override
	public void transfer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
       Random r = new Random();
		while(true) {    //一直循环 拿锁
		if(from.getLock().tryLock()) {
    	  System.out.println(Thread.currentThread().getName() + "get" + from.getName());
    	  try {
    		  if(to.getLock().tryLock()) {
    			  try {
    				  System.out.println(Thread.currentThread().getName()  + " get"  + to.getName());
    				  from.flyMoney(amount);
    				  to.addMoney(amount);
    				  System.out.println(from);
    				  System.out.println(to);
    				  break;     //成功就ok
    			  }finally {
    				  to.getLock().unlock();
    			  }
    		  }
    	  }finally {
    		  from.getLock().unlock();
    	  }
      }
		Thread.sleep(r.nextInt(2));   //休眠防止造成活锁
       }
	}

}
