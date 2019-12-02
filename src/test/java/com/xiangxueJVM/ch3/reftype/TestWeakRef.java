package com.xiangxueJVM.ch3.reftype;

import java.lang.ref.WeakReference;

//虚引用   ，只要触发gc就回收
public class TestWeakRef {
	public static class User {
		int id = 0;
		String name = "";
		public User(int id, String name) {
			this.id = id;
			this.name = name;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + "]";
		}
	}
	public static void main(String[] args) {
		User u = new User(1,"juek");
		WeakReference<User> userWeak = new WeakReference<User>(u);
		u = null; //干掉强引用，确保这个实例只有userWeak的弱引用
		System.out.println(userWeak.get());
		System.gc();   // 进行一次强行的gc
		System.out.println("After gc");
		System.out.println(userWeak.get());
	}
	
}
