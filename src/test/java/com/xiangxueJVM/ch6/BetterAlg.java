package com.xiangxueJVM.ch6;

import java.util.Scanner;

/**
*
*类说明：选择更优的算法
*/
public class BetterAlg {
    //如何判断一个数为2的多少次方
	@SuppressWarnings("resource")
	public static void main(String[] args) {
	 int n = 2 ;
	 Scanner scanner = new Scanner(System.in);
	 System.out.println("请 输入需要计算 的数");
	 while(scanner.hasNext()) {   //控制台输入
		 int input = scanner.nextInt();
//		 while(true) {
//			 if(input == n) {
//				 System.out.println("是（"+n+"）的次方 ");
//				 break;
//			 }
//			 if(input%n != 0) {
//				 System.out.println("不是（"+n+"）的次方");
//				 break;
//			 }else {
//				 input = input/2;
//			 }
//		 }
		 
		   if((input&(input-1)) ==0){  //位运算 ，
               System.out.println("是("+n+")的次方");
           }else{
               System.out.println("不是("+n+")的次方");
           }
	 }
   }
}
