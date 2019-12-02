package com.xiangxue.ch2.forkjoin;

import com.xiangxue.ch1.SleepTools;

public class SingleThreadCount {
  public static void main(String[] args) {
	int count = 0 ;
	int[] src = MakeArray.makeArray();
	
	long start = System.currentTimeMillis();
	for(int i =0 ;i<src.length; i++) {
		//
		SleepTools.secondS(1);
		count = count + src[i];
	}
	System.out.println("send time:"+ (System.currentTimeMillis()-start)+"ms");
}
}
