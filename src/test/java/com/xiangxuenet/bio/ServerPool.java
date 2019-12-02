package com.xiangxuenet.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerPool {
  private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  
  public static void main(String[] args) throws IOException {
	@SuppressWarnings("resource")
	ServerSocket serverSocket = new ServerSocket();
	serverSocket.bind(new InetSocketAddress(8899));
	System.out.println("Start  Server.....");
	for(;;) {
		executorService.execute(new ServerTask(serverSocket.accept()));
	}
}

  private static class ServerTask implements Runnable{
    private Socket socket  = null;
    
    public ServerTask(Socket socket) {
      this.socket =socket;
    }
    
	@Override
	public void run() {
		 ObjectInputStream inputStream = null;
		 ObjectOutputStream outputStream = null;
	 try {
	     inputStream = new ObjectInputStream(socket.getInputStream());
		 outputStream = new ObjectOutputStream(socket.getOutputStream());
		 
		 String userName = inputStream.readUTF();
		 System.out.println("接收到的:"+userName);
		 
		 outputStream.writeUTF("hello"+userName);
		 outputStream.flush();
	 }catch (Exception e) {
        e.printStackTrace();
	 }finally {
		 try {
			 inputStream.close();
			 outputStream.close();
			 socket.close();
		 }catch (Exception e) {
           e.printStackTrace();
		 }
	 }
		
	}
	  
  }
	
}
