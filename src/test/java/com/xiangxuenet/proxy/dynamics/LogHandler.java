package com.xiangxuenet.proxy.dynamics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogHandler implements InvocationHandler {
     //动态代理类智能够代理 接口 ，不 能代理 其他的
	
	//目标对象
	private Object targetObject; 
	
	// 绑定关系，也就是关联到哪个 接口（与具体的实现类绑定）的哪些方法将被调用执行，执行invoke方法
	public Object newProxyInstance(Object targetObject) {
		this.targetObject = targetObject;
		//第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口  
		//根据传入的目标返回一个代理对象
		//第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法 
		//return null; //返回method方法的返回值(如果有);
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader()
				, targetObject.getClass().getInterfaces(), this);
	}
	
	/**
	 * proxy表示代理的那个对象，method表示原对象被调用的方法，args表示方法的参数，
	 * //return null; //返回method方法的返回值(如果有);
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("strart-->>");
//		         for(int i = 0 ;i<args.length;i++) {
//		        	 System.out.println(args[i]);
//		         }
				System.out.println(args[0]);
		         try {
		        	 System.out.println("调用前的处理");
		        	 method.invoke(targetObject, args);
		        	 System.out.println("调用后的处理");
		         }catch (Exception e) {
		           e.printStackTrace();
		           System.out.println("error-->>");
		         }
			}
		}).start();
		
        return null;
	}

}
