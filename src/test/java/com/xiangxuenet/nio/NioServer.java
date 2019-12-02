package com.xiangxuenet.nio;

public class NioServer {
	private static NioServerHandle nioServerHandle;
	
	public static void start() {
		if(nioServerHandle != null) {
			nioServerHandle.stop();
			nioServerHandle = new NioServerHandle(Const.DEFAULT_PORT);
		    new Thread(nioServerHandle,"Server").start();
		}
	}
	
	public static void main(String[] args) {
		start();
	}

}
