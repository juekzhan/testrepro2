package com.xiangxueJVM.ch7.p7;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//复合优先于继承
public class CompHashSet<E> {
	private final Set<E> s;
	private int addCount = 0;//特殊需求：元素个数
	
	public CompHashSet(Set<E> s) {
		this.s = s;
	}
	
	//添加一个元素
	public boolean add(E e) {
		addCount++;
		return s.add(e);
	}
	//添加一组元素
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return s.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}
    
	public static void main(String[] args) {
		CompHashSet<String> s = new CompHashSet<String>(new HashSet<String>());
		s.addAll(Arrays.asList("juek", "yanwuy", "Deer"));
		System.out.println(s.getAddCount());
	}
}
