package com.xiangxueJVM.ch5.memleak;
//手写一个栈  内存泄露
public class MyStack {
	 public Object[] elements;//数组来保存
	    private int size =0;
	    private static final int Cap = 16;

	    public MyStack() {
	        elements = new Object[Cap];
	    }

	    public void push(Object e){ //入栈
	        elements[size] = e;
	        size++;
	    }
	    public Object pop(){  //出栈
	    	size = size -1;
	        Object o = elements[size];
	       // elements[size] = null;  //让GC 回收掉
	        return o;
	    }
}
