package com.xiangxue.ch4.aqs;

import java.util.concurrent.locks.Lock;

import com.xiangxue.ch1.SleepTools;

public class TestReenterSelfLock {
 static final Lock lock = new ReenterSelfLock();
 
 public void reeter(int x) {
	 lock.lock();
	 try {
		 System.out.println(Thread.currentThread().getName()+"递归层级"+x);
		 int y = x - 1;
		 if(y == 0) {
			 return;
		 }else{
			reeter(y);
		 }
	 }finally {
		 lock.unlock();
	 }
 }
 
 
 public void test() {
	 class Worker extends Thread{

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
			SleepTools.secondS(1);
			reeter(3);
		}
	 }
	 
	 for(int i = 0 ; i<3; i++) {
		 new Worker().start();
	 }
	 
	 for(int j = 0;j<100;j++) {
		 SleepTools.secondS(1);
	 }
 }
 

 public static void main(String[] args) {
	new TestReenterSelfLock().test();
}
 
}
