package com.xiangxuenet.proxy.dynamics;

import com.xiangxuenet.proxy.statics.UserManager;

public class Client {

	public static void main(String[] args) {
		 LogHandler logHandler=new LogHandler(); 
		 UserManager userManager=(UserManager)logHandler.newProxyInstance(new UserManagerImpl()); 
		 userManager.modifyUser("1111", "张三"); 
		 
		 System.out.println("直接结束");
	}

}
