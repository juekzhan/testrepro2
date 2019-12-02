package com.xiangxueJVM.ch7.p11;
/**
 * 优先使用标准的异常
 * 要尽量追求代码的重用，同时减少类加载的数目，提高类装载的性能。
常用的异常：
IllegalArgumentException    -- 调用者传递的参数不合适
IllegalStateException    -- 接收的对象状态不对，
NullPointerException   
UnsupportedOperationException  –不支持的操作
 */
public class StandardException {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
        int count =0;
        if(count<0){//调用者传递的参数不合适
            throw new IllegalArgumentException("");
        }
        int state =0;
        if(state!=1){//接收的对象状态不对
            throw new IllegalStateException("");
        }
        Object o = new Object();
        if(o==null){//接收的对象为空
            throw new NullPointerException("");
        }
        //返回结果---找不到对象！
    }
	
	 protected boolean tryAcquire(long arg) {
	        throw new UnsupportedOperationException();
	    }
}
