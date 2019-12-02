package com.xiangxueJVM.ch1;

public class ConstantPool {
  public static void main(String[] args) {
	  String b = "a学";
      String a = b + "B堂";
      System.out.println(a.intern() == a);
}
}
