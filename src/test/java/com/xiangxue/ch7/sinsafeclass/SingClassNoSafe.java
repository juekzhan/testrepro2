package com.xiangxue.ch7.sinsafeclass;

/**
 * 
 * @ClassName: SingClassNoSafe  
 * @Package :com.xiangxue.ch7.sinsafeclass
 * @Description: 双重检查不安全的
 * @Author: shuling.zhan
 * @Date: 2018年7月12日 下午2:54:05
 */
public class SingClassNoSafe {
  private static SingClassNoSafe singClassNoSafe ;
  
  private SingClassNoSafe() {}
  
  public static SingClassNoSafe getInstance() {
	  if(singClassNoSafe == null) { //第一次检查不加锁
		  System.out.println(Thread.currentThread() + "is null");
		  synchronized (SingClassNoSafe.class) {
			if(singClassNoSafe == null) {//第二次检查加锁      
				System.out.println(Thread.currentThread() + "is null");
				singClassNoSafe = new SingClassNoSafe();
				//但是为线程不安全的单例模式
				//因为虚拟机加载的可能不同
				//1.分配内存空间  2.初始化堆内存空间3.把对象指向内存空间
				//假如 内存空间没有分配，但是已经指向了 栈的位置， 就是3.排到最前面   ，就会出现NullPointerException （）
			}
		}
	  }
	  return singClassNoSafe;
  }
}
