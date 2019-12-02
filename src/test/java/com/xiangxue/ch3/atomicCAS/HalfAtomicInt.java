package com.xiangxue.ch3.atomicCAS;

import java.util.concurrent.atomic.AtomicInteger;

import com.xiangxue.ch1.SleepTools;

/**
 * 
 * @ClassName: HalfAtomicInt  
 * @Package :com.xiangxue.ch3.atomicCAS
 * @Description: cas 原理实现 实现相关的东西
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年5月9日 下午2:54:44
 */
public class HalfAtomicInt {
  private AtomicInteger atomicI = new AtomicInteger(0);
  
  
  //比较完成
  public void increament() {
	  while(true) {
		  int i = getCount();
		  boolean suc = compareAndSet(i, ++i);
		  if(suc) {
			  break;
		  }
	  }
  }
	
  
  public int getCount() {
	  return atomicI.get();
  }
  
  public boolean compareAndSet(int oldValue,int newValue) {
	  return atomicI.compareAndSet(oldValue, newValue);
  }
  
	public static void main(String[] args) {
		HalfAtomicInt atomicInt = new HalfAtomicInt();
		for(int i = 0;i<10;i++ ) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
                  for(int i =0 ; i<1000 ;i++) {
                	  atomicInt.increament();
                  }
				}
			}).start();
		}
		SleepTools.secondS(1000);
		System.out.println(atomicInt.getCount());
	}
}
