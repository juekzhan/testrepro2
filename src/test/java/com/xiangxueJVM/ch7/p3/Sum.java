package com.xiangxueJVM.ch7.p3;
//创造不必要的对象
public class Sum {
 public static void main(String[] args) {
	long start =System.currentTimeMillis();
	//Long sunm = 0L;  //就new出来了 对象了
	long sum = 0L;  //这个速度快 ，一般不要用对象 因为对象要再堆中 经过类加载器进行相应的构造 ，然后再堆中分配
	    //在栈中分配
	for (long i = 0; i < Integer.MAX_VALUE; i++) {//21亿次
		sum += i;
	}
	System.out.println(sum);
	System.out.println("spend time:"+(System.currentTimeMillis()-start)+"ms");
}
}
