package com.xiangxuenet.proxy.refe;

import java.lang.reflect.Method;

//演示反射使用
public class RefleDemo {

	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) throws Exception {
        //实例化对象的标准用法,也就是所谓 的正
		Servant servant = new Servant();
        servant.service("Hello");
        
        //通过全限定名拿到累的class对象
        Class servantClazz = Class.forName("com.xiangxuenet.proxy.refe.Servant");
        //通过class 对象拿到类的实例
        Servant shapeInt = (Servant)servantClazz.newInstance();
        //通过class对象拿到方法列表
        Method[] methods = servantClazz.getMethods();
        for(Method method : methods) {
        	System.out.println(method.getName() + "  HHH"); //打印方法
        	if(method.getName().equals("toString")) {
        		try {
        			 method.invoke(servantClazz.newInstance(), null);
        		}catch (Exception e) {
                  e.printStackTrace();
        		}
        	}
        }
        		
	}

}
