package com.xiangxuenet.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * 
 * @ClassName: JamesDyna  
 * @Package :com.xiangxuenet.proxy.dynamic
 * @Description: 动态代理对象，代理所有的Object类
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年11月13日 下午3:15:34
 */
public class JamesDyna implements InvocationHandler{
    //实现反射的接口InvocationHandler
	private Object receptionist;

    public JamesDyna(Object receptionist) {
        this.receptionist = receptionist;
    }
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		   System.out.println("我是代理 我骄傲 ，我 自豪 ");
		  //就是代理对象中所有的方法参数
		   Object result = null;
		   try {
	         result = method.invoke(receptionist,args);
		   }catch (InvocationTargetException e) {
			   System.out.println(e.getCause());
		   }
	        System.out.println("代理 完成 我赚到钱了");
		return result;
	}

}
