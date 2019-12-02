package com.xiangxue.ch4.condition;

import com.xiangxue.ch1.SleepTools;

public class ConditionTest {
  private final static ExpressCondition expressCondition = new ExpressCondition(100, ExpressCondition.CITY);
  
  public static class SitThread extends Thread{
	  public void run() {
		  expressCondition.waitSite();
	  }
  }
  
  
  public static class KmThread extends Thread{
	  public void run() {
		  expressCondition.waitKm();
	  }
  }
  
  
  public static void main(String[] args) {
	for(int i = 0;i<3;i++) {
		new SitThread().start();
	}
	
	for(int j = 0 ;j<3;j++) {
		new KmThread().start();
	}
	
	SleepTools.second(2);
	expressCondition.changeKm();
}
  
}
