package com.xiangxuenet.bio.rcpserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServerFrame {
	private static ExecutorService executorService = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	// 服务注册中心
	@SuppressWarnings("rawtypes")
	private static final Map<String, Class> serviceHolder = new HashMap<>();

	// 服务端口号
	private int port;

	public RpcServerFrame(int port) {
		this.port = port;
	}

	// 服务注册
	@SuppressWarnings("rawtypes")
	public void registerService(String className, Class impl) {
		serviceHolder.put(className, impl);
	}
	
	//处理服务请求任务
	private static class ServerTask implements Runnable{
        private Socket client = null;
        
		public ServerTask(Socket client) {
			this.client = client;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void run() {
          try(ObjectInputStream inputStream =
        		    new ObjectInputStream(client.getInputStream());
        	   ObjectOutputStream outputStream =
        			new ObjectOutputStream(client.getOutputStream())){
        	  
        	  //方法所在的类名，接口名‘
        	  String serviceName = inputStream.readUTF();
        	  //方法的名字
        	  String methodName = inputStream.readUTF();
        	  //方法的入参类型
        	  Class<?>[] parmTypes = (Class<?>[]) inputStream.readObject();
        	  //方法的入参值
        	  Object[] args = (Object[]) inputStream.readObject();
        	  
        	  Class serviceClass = serviceHolder.get(serviceName);
        	  if(serviceClass == null) {
        		  throw new ClassNotFoundException(serviceName+" Not Found xxxxx");
        	  }
        	  
        	  Method method = serviceClass.getMethod(methodName, parmTypes);
        	  Object result = method.invoke(serviceClass.newInstance(), args);
        	  
        	  outputStream.writeObject(result);
        	  outputStream.flush();
          }catch (Exception e) {
             e.printStackTrace();
          }finally {
        	  try {
                  client.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
		  }
		}
		
	}
	
	//启动服务
	@SuppressWarnings("resource")
	public void startService() throws IOException{
		ServerSocket  serverSocket = new ServerSocket();
		serverSocket.bind(new InetSocketAddress(port));
		System.out.println("RPC server on ："+ port + "运行");
		try {
			for(;;) {
				executorService.execute(new ServerTask(serverSocket.accept()));
			}
		}finally {
			
		}
	}
}
