 package com.xiangxue.ch2.forkjoin;

import java.util.Random;

public class MakeArray {
  //数组长度
	public static final int ARRAY_LENGTH = 4000;
	
	public final static int THRESHOLD  = 47;
	
	public static int[] makeArray() {
		//new 一个随机发生数 
		Random r = new Random();
		int[] result  = new int[ARRAY_LENGTH];
		for(int i =0 ;  i<ARRAY_LENGTH ;i++) {
			result[i] = r.nextInt(ARRAY_LENGTH * 3);
		}
		return result;
	}
	
	public static void main(String[] args) {
		int  result[] = MakeArray.makeArray();
		for(int i = 0;i< MakeArray.ARRAY_LENGTH ; i++) {
			System.out.println(result[i]);
		}
	}
}
