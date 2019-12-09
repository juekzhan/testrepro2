package com.xiangxuenet.nio;

public class NioServer {
	private static NioServerHandle nioServerHandle;
	
	public static void start11() {
		if(nioServerHandle != null) {
			nioServerHandle.stop();
		}else{
			nioServerHandle = new NioServerHandle(Const.DEFAULT_PORT);
		    new Thread(nioServerHandle,"Server").start();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("dddddd");
		start11();
	}

}
