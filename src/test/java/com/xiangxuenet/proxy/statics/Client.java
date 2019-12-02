package com.xiangxuenet.proxy.statics;

public class Client {

	public static void main(String[] args) {
         UserManager userManager  = new UserManagerProxy(new UserManngerImpl());
         userManager.addUser("111","展示");
	}

}
