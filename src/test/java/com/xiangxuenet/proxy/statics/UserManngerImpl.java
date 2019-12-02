package com.xiangxuenet.proxy.statics;

public class UserManngerImpl implements UserManager {

	@Override
	public void addUser(String userId, String userName) {
		System.out.println("UserManngerImpl.addUser");
		
	}

	@Override
	public void delUser(String userId) {
		System.out.println("UserManngerImpl.delUser");
		
	}

	@Override
	public void findUser(String userId) {
		System.out.println("UserManngerImpl.findUser");
		
	}

	@Override
	public void modifyUser(String userId, String userName) {
		System.out.println("UserManngerImpl.modifyUser");
	}
    
}
