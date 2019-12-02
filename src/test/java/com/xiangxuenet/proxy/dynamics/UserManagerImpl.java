package com.xiangxuenet.proxy.dynamics;

import com.xiangxuenet.proxy.statics.UserManager;

public class UserManagerImpl implements UserManager {

	@Override
	public void addUser(String userId, String userName) {
		System.out.println("UserManagerImpl.addUser");
	}

	@Override
	public void delUser(String userId) {
		System.out.println("UserManagerImpl.delUser");  
	}

	@Override
	public void findUser(String userId) {
		System.out.println("UserManagerImpl.findUser"); 
	}

	@Override
	public void modifyUser(String userId, String userName) {
		System.out.println("UserManagerImpl.modifyUser");  
	}

}
