package com.xiangxueJVM.ch3.reftype;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;


//软引用
public class TestSoftRef {
  //对象
	public static class User{
		public int id = 0;
		public String name = "";
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
		User u = new User(1, "juek"); //new 是强引用
		SoftReference<User> userSoft = new SoftReference<User>(u);   //这是软引用，就是在堆内存满的时候会去回收
		u = null;  //先把强引用干掉，确保这个实例只有userSoft的软引用
		System.out.println(userSoft.get()); //看这个对象还存在不
		System.gc();  //进行一次gc   这块前往不要写在业务代码中 ，否者用户的线程会挂起(跟垃圾回收器的算法有关)
		System.out.println("Afer gc");
		System.out.println(userSoft.get());    // 依旧存在
		//往堆中填充数据，导致OOM
		List<byte[]> list = new LinkedList<>();
		try {
			for(;;) {
				System.out.println("***************"+userSoft.get());
				list.add(new byte[1024*1024*1]);
			}
		}catch (Throwable e) {    // 不进行抛出 继续运行
          System.out.println("Exception................"+userSoft.get());    //发生了异常进行查看， 回收
		}
		
	}
}
