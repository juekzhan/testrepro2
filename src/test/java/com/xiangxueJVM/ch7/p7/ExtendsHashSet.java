package com.xiangxueJVM.ch7.p7;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
// 这个会重复添加的 ，破坏了类的结构
//优先使用复合胜过继承
// 继承类：统计增加的个数
//
@SuppressWarnings("serial")
public class ExtendsHashSet<E> extends HashSet<E> {
	private int addCount = 0;//计数器（每加一个元素就+1）

	public ExtendsHashSet() {
	}

	public ExtendsHashSet(int initCap, float loadFactor) {
		super(initCap, loadFactor);
	}

	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);    //因为这个有调用了 add的方法
	}

	public int getAddCount() {
		return addCount;
	}

	public static void main(String[] args) {
		ExtendsHashSet<String> s = new ExtendsHashSet<String>();
		s.addAll(Arrays.asList("adsd", "Markasd", "asdeer"));
		System.out.println(s.getAddCount());
	}
}
