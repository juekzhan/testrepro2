package com.xiangxue.ch7.safeclass.safepublish;


/**
 * 
 * @ClassName: SafePublcFinalUser  
 * @Package :com.xiangxue.ch7.safeclass.safepublish
 * @Description: 委托给线程安全类去做 
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月19日 下午3:13:36
 */
public class SafePublcFinalUser {
 private final SynFinalUser synFinalUser;
 
 public SynFinalUser getUser() {
	 return synFinalUser;
 }
 
 
 
 public SafePublcFinalUser(FinalUser finalUser) {
	this.synFinalUser = new SynFinalUser(finalUser);
}



public static class  SynFinalUser{
	 private final FinalUser finalUser;
	 private final Object lock = new Object();
	 
	 public SynFinalUser(FinalUser vo) {
		 this.finalUser = vo;
	 }
	 
	 public int getAge() {
		 synchronized (lock) {
		  return finalUser.getAge();	
		}
	 }
	 
	 public void setAge(int age) {
		 synchronized (lock) {
			 finalUser.setAge(age);
		}
	 }
 }
}
