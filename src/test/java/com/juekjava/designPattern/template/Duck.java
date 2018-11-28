package com.juekjava.designPattern.template;
/**
 * Arrays 的方法中 comparable 接口 属于Arrays 中的方法
 * @author zsh12489
 *
 */
public class Duck implements Comparable<Object> {
     String name;
     int weight;
     
	public Duck(String name, int weight) {
		super();
		this.name = name;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return name + "weighs " + weight;
	}
	@Override
	public int compareTo(Object obj) {
		Duck otherDuck = (Duck) obj;
		if(this.weight < otherDuck.weight) {
			return -1;
		}
		if(this.weight == otherDuck.weight) {
			return 0;
		}
		if(this.weight > otherDuck.weight) {
			return 1;
		}
		return 0;
	}

}
