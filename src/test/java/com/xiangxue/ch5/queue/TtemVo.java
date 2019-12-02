package com.xiangxue.ch5.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/*存在的队列的元素*/
public class TtemVo<T> implements Delayed {
    //到期时间
	private long activeTime;
	//业务数据 泛型
	private T data;
	
	public TtemVo(long expirationTime, T data) {
		this.activeTime = expirationTime*1000 + System.currentTimeMillis();   //传入过期时间 为当前时间加出入的秒
		this.data = data;
	}

	
	public long getActiveTime() {
		return activeTime;
	}


	public T getData() {
		return data;
	}

	/*
	 *Delayed接口继承了Comparable接口，按剩余时间排序，实际计算考虑精度为纳秒数
	 */
	@Override
	public int compareTo(Delayed o) {
		long d = (getDelay(TimeUnit.MILLISECONDS)
				- o.getDelay(TimeUnit.MILLISECONDS));
		if (d==0){
			return 0;
		}else{
			if (d<0){
				return -1;
			}else{
				return  1;
			}
		}
	}
     /**
      * 这个 方法返回到激活日期的剩余 时间，时间单位由 单位参数指定   就是返回上面指定的时间，就是传上面返回什么
      */
	@Override
	public long getDelay(TimeUnit unit) {
        long d = unit.convert(this.activeTime-System.currentTimeMillis(),unit);
		return d;
	}
    
}
