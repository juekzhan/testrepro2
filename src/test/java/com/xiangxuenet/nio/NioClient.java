package com.xiangxuenet.nio;

import java.util.Scanner;

public class NioClient {
 private static NioClientHandle nioClientHandle;
 
 public static void start() {
	 if(nioClientHandle != null) {
		 nioClientHandle.stop();
		 nioClientHandle = new NioClientHandle(Const.DEFAULT_SERVER_IP, Const.DEFAULT_PORT);
		 new Thread(nioClientHandle).start();
	 }
 }
 //向服务器发送消息
 public static boolean sendMsg(String msg) throws Exception{
	 nioClientHandle.sendMsg(msg);
	 return true;
 }
 
 @SuppressWarnings("resource")
public static void main(String[] args) throws Exception{
	start();
	Scanner scanner = new Scanner(System.in);
	while(nioClientHandle.sendMsg(scanner.next())) {;
	
	}
}
}
