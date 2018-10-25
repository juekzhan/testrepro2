package com.juekjava.designPattern.template;

/**
 * 超基类
 * 
 * @author zsh12489
 *
 */
public abstract class CaffeeineBeverage {
	/**
	 * 所有的通用方法（这个就是模板方法） final 定义 子类不能覆盖的
	 */
	final void prepareRecipe() {
		boilWater();
		brew();
		addCondiments();
		pourInCup();
		// 在这加上个条件 ，而该条件的成立 ，是由一个具体的方法 下面决定的，就是钩子，
		// 比如客人想要加调料
		if (customerWantsCondoments()) {
			addMike();
		}
	}

	/**
	 * 再定义一个模板方法
	 */

	final void concreteOperation() {

	}

	abstract void brew();

	abstract void addCondiments();

	void addMike() {
		System.out.println("Adding to Milke");
	}

	void boilWater() {
		System.out.println("Boiling Water");
	}

	void pourInCup() {
		System.out.println("Pouring into cup");
	}

	/**
	 * 在这里定义一个方法，（通常）是空的缺省实现，这个方法只会返回true，不会做别的事情 这就是一个钩子方法，子类可以覆盖这个方法，但是不一定要这么做
	 * 
	 * @return
	 */
	boolean customerWantsCondoments() {
		return true;
	}
}
