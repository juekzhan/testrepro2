package com.xiangxue.ch1;

public class SleepTools {
  public static void second(int second ) {
	  try {
		  Thread.sleep(second*1000);
	  }catch (Exception e) {
	}
  }
  
  public static void secondS(int second ) {
	  try {
		  Thread.sleep(second);
	  }catch (Exception e) {
	}
  }
}
