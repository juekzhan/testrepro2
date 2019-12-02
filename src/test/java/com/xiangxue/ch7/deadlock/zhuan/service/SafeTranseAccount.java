package com.xiangxue.ch7.deadlock.zhuan.service;

import com.xiangxue.ch7.deadlock.zhuan.UserAccount;

/**
 * 
 * @ClassName: SafeTranseAccount  
 * @Package :com.xiangxue.ch7.deadlock.zhuan.service
 * @Description: 强制调换顺序 或者再加第三把锁
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月19日 下午6:07:28
 */
public class SafeTranseAccount implements ITranfer {
   
	private static Object tieLock = new Object();
	
	@Override
	public void transfer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
		
		int fromHash = System.identityHashCode(from);
		int toHash = System.identityHashCode(to);
        
		if(fromHash < toHash) {
			synchronized (from) {
				System.out.println(Thread.currentThread().getName()  +" get " + from.getName());
				synchronized (to) {
				  System.out.println(Thread.currentThread().getName() +" get " + to.getName());
				  from.flyMoney(amount);
				  to.addMoney(amount);
				  System.out.println(from);
				  System.out.println(to);
				}
			}
		}else if(toHash < fromHash) {
			synchronized (to) {
				System.out.println(Thread.currentThread().getName()  +" get " + from.getName());
				synchronized (from) {
				  System.out.println(Thread.currentThread().getName() +" get " + to.getName());
				  from.flyMoney(amount);
				  to.addMoney(amount);
				  System.out.println(from);
				  System.out.println(to);
				}
			}
		}else {
			synchronized (tieLock) {
				synchronized (from) {
					System.out.println(Thread.currentThread().getName()  +" get " + from.getName());
					synchronized (to) {
					  System.out.println(Thread.currentThread().getName() +" get " + to.getName());
					  from.flyMoney(amount);
					  to.addMoney(amount);
					  System.out.println(from);
					  System.out.println(to);
					}
				}
			}
		}
	}

}
