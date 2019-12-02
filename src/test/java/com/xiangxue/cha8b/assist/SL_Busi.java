package com.xiangxue.cha8b.assist;

public class SL_Busi {
  public static  void buisness(int sleepTime) {
	  try {
		  Thread.sleep(sleepTime);
	  }catch (Exception e) {
        e.printStackTrace();
	  }
  }
}
