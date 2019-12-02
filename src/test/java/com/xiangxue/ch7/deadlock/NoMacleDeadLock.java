package com.xiangxue.ch7.deadlock;
/**
 * 
 * @ClassName: NoMacleDeadLock  
 * @Package :com.xiangxue.ch7.deadlock
 * @Description: 死锁 例子
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月19日 下午3:59:40
 */
public class NoMacleDeadLock {
 private static Object lockFirst = new Object();
 
 private static Object lockSencod = new Object();
 
 //先拿第一个锁 再拿第二个锁
 private static void firstToSencod() throws Exception{
	 synchronized (lockFirst) {
	  	System.out.println("Thread Name" + Thread.currentThread().getName() + "get 1st");
	  	synchronized (lockSencod) {
		 System.out.println("Thread name" + Thread.currentThread().getName()  + "get 2nd");	
		}
	 }
 }
 
 //先拿第二锁 再拿第一个锁
 private static void sencodToFirst() throws Exception{
	 synchronized (lockSencod) {
		 System.out.println("Thread Name" + Thread.currentThread().getName() + "get 2nd");
		 synchronized (lockFirst) {
			 System.out.println("Thread name" + Thread.currentThread().getName()  + "get 1st");	
		}
	 }
 }
 
 private static class TestThread extends Thread{
	 private String name;
	 public TestThread(String name) {
		 this.name = name;
	 }
	@Override
	public void run() {
		Thread.currentThread().setName(name);
		try {
			firstToSencod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
 }
 
 public static void main(String[] args) {
	Thread.currentThread().setName("MainTestThread");
	TestThread testThread = new TestThread("SubTestThread");
	testThread.start();
	try {
		sencodToFirst();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
}
