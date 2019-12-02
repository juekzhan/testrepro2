package com.xiangxue.ch7.sinsafeclass;
/**
 * 
 * @ClassName: SingLan  
 * @Package :com.xiangxue.ch7.sinsafeclass
 * @Description: 懒汉模式    延迟初始化
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月22日 下午4:04:36
 */
public class SingLan {
   private SingLan() {}
   
   private static class InstanceHolder{
	   private static SingLan intance = new SingLan();
   }
   //如果要用就用这个 拿到内部类的实例，因为 JVM 在 加载CLASS 的时候  加锁了 ，还有一个CLASS在JVM中只存在一个
   //就是在加载类的时候，就初始化了static 的东西
   public static SingLan getIntance() {
	   return InstanceHolder.intance;
   }
}
