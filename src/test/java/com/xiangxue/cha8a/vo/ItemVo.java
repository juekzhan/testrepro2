package com.xiangxue.cha8a.vo;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
/*存在的队列元素*/
public class ItemVo<T> implements Delayed {
    private long timeOutValue;   //到期时间
	
	private T data;   //存放数据
	
	public long getTimeOutValue() {
		return timeOutValue;
	}

	public T getData() {
		return data;
	}
     /**
      * 
      * @param 单位秒吧 
      * @param data
      */
	public ItemVo(long expirationTime, T data) {
		super();
		this.timeOutValue = expirationTime * 1000 + System.currentTimeMillis();
		this.data = data;
	}
     
	/**
	 * 其实这个就 是 返回 一个相当boolean 值的一个整型
	 */
	@Override
	public int compareTo(Delayed o) {
	    long d = (getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
	    if(d == 0) {
	    	return 0;
	    }else if(d <  0) {
	    	return -1;
	    }else {
	    	return 1;
	    }
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long d = unit.convert(this.timeOutValue - System.currentTimeMillis(), unit);
		return d;
	}
 
}
