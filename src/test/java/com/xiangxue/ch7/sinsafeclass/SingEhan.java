package com.xiangxue.ch7.sinsafeclass;
/**
 * 
 * @ClassName: SingEhan  
 * @Package :com.xiangxue.ch7.sinsafeclass
 * @Description: 饿汉模式， 就是一个安全的单例模式 
 * 枚举也可以 恶汉式的
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月22日 下午4:10:15
 */
public class SingEhan {
 private SingEhan() {}
 private static SingEhan singEhan = new SingEhan();    //JVM虚拟机就只加载一次就OK 了  JVM在加载CLASS 的 时候加锁，Class的static ，就加上个锁
 
 public static SingEhan getIntance() {
	 return singEhan;
 }
 
}
