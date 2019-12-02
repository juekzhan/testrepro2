package com.xiangxue.ch7.safeclass;

import net.jcip.annotations.ThreadSafe;

/**
 * 
 * @ClassName: ImmNoChaned
 * @Package :com.xiangxue.ch7.safeclass
 * @Description: 线程完全 ，属性不可变
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月19日 下午1:50:27
 */
@ThreadSafe
public class ImmNoChaned {
	private final int a = 0; // 不可变 常量线程安全

	private final UserVo user = new UserVo();   //这个是线程不安全的， 因为是引用实例

	public int getA() {
		return a;
	}

	public UserVo getUser() { // 做任意的
		return user;
	}

}
