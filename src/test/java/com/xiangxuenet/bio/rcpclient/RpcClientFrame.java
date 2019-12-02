package com.xiangxuenet.bio.rcpclient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * 
 * @ClassName: RpcClientFrame  
 * @Package :com.xiangxuenet.bio.rcpclient
 * @Description: RPC框架的代理部分
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年11月13日 下午3:05:36
 */
public class RpcClientFrame {
   // 根据传入的方法参数返回调用 /通过代理类访问实际 的对象
	@SuppressWarnings("unchecked")
	public static<T> T getRemoteProxyObj(final Class<?> serviceInterface,
            String hostname,int port) {
		final InetSocketAddress addr  = new InetSocketAddress(hostname,port);
		return (T)Proxy.newProxyInstance(serviceInterface.getClassLoader(),
				new Class<?>[] {serviceInterface},
				new DynProxy(serviceInterface,addr));
	}
	
	private static class DynProxy implements InvocationHandler{
        
		private final Class<?> serviceInterface;
		
		private final InetSocketAddress addr;
		
		public DynProxy(Class<?> serviceInterface, InetSocketAddress addr) {
			this.serviceInterface = serviceInterface;
			this.addr = addr;
		}


		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			    System.out.println(serviceInterface.getName());
			    System.out.println(method.getName());
			    System.out.println(method.getParameterTypes());
			    
			    
			    Socket socket = null;
	            ObjectOutputStream output = null;
	            ObjectInputStream input = null;
	            try {
	            	socket = new Socket();
	                socket.connect(addr);
	                
	                output = new ObjectOutputStream(socket.getOutputStream());
	                output.writeUTF(serviceInterface.getName());//方法所在的类
	                output.writeUTF(method.getName());//方法的名
	                output.writeObject(method.getParameterTypes());//方法的入参类型
	                output.writeObject(args);
	                output.flush();
	                
	                input = new ObjectInputStream(socket.getInputStream());
	                return input.readObject();
	            }finally {
	              if (socket!=null) socket.close();
	              if (output!=null) output.close();
	              if (input!=null) input.close();
				}
		}

	
	}
	
}
