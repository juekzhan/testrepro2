package com.xiangxue.ch3.atomicCAS;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 
 * @ClassName: AtomicreFerenceUse  
 * @Package :com.xiangxue.ch3.atomicCAS
 * @Description: 线程 乐观锁对象应用
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年5月9日 下午2:17:22
 */
public class AtomicreFerenceUse {
	static AtomicReference<Peron> atomicReference;
	
 public static void main(String[] args) {
	Peron peron = new Peron("jj", 22);
	atomicReference = new AtomicReference<AtomicreFerenceUse.Peron>(peron);
	Peron updateP = new Peron("bb", 11);
	
	System.out.println("更改前：");
	System.out.println(atomicReference.get());
	System.out.println("更改 后：");
	atomicReference.compareAndSet(peron, updateP);
	
	System.out.println(atomicReference.get());
	System.out.println(peron);
 }
 
 static class Peron {
	 private String name;
	 private int age;
	public Peron(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	
	public String getName() {
		return name;
	}


	public int getAge() {
		return age;
	}


	@Override
	public String toString() {
		return "Peron{" +"name = " + name +";age="+age+"}";
	}
	 
	 
 }
}
