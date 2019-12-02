package com.xiangxueJVM.ch2.gengric;
/**
 * 泛型方法
 * 引入一个类型变量T（其他大写字母都可以，不过常用的就是T，E，K，V等等）
 */
public class GenericMethod {
   //泛型方法
	public <T> T genericMethod(T t) {
	 return t;
    }
   //普通方法
   public void test(int x ,int y) {
	   System.out.println(x+y);
   }
   public static void main(String[] args) {
	   GenericMethod genericMethod = new GenericMethod();
       genericMethod.test(13,7);
       System.out.println(genericMethod.<String>genericMethod("King"));
       System.out.println(genericMethod.genericMethod(180));
}
}
