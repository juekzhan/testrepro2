package com.xiangxuenet.proxy;
/**
 * 
 * @ClassName: Receptionist  
 * @Package :com.xiangxuenet.proxy
 * @Description: 前台小姐姐类
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年11月13日 下午3:11:28
 */
public class Receptionist implements IGetServer {

	@Override
	public void choice(String desc) {
		
		System.out.println("已经为你选好了...................【"+desc+"】");
		int i = 5/0;
		throw new RuntimeException("抛出去个异常看看");
	}

}
