package com.xiangxueJVM.ch2;

public class ObjectAlloc {
 public static class User{
	 public int id = 0;   //在数据区存储
	 public String name = "";  
	   // 每个对象都有个头指针
	 User(){
		 
	 }
 }
 
 public static void alloc() {
	 User u = new User(); //在 堆上分配 
	 u.id = 5;
	 u.name = "juek";   //在数据区分配
 }
 
 public static void main(String[] args) {
	alloc();
}
 
}
