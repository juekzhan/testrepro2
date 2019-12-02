package com.xiangxuenet.proxy.statics;

public class UserManagerProxy implements UserManager {
    
	private UserManager userManager;
	
	public UserManagerProxy(UserManager userManager) {
		this.userManager=userManager;
	}
	@Override
	public void addUser(String userId, String userName) {
		//TDO
		userManager.addUser(userId, userName);
		//TDO
	}
	@Override
	public void delUser(String userId) {
		userManager.delUser(userId);
	}
	@Override
	public void findUser(String userId) {
		userManager.findUser(userId);
	}
	@Override
	public void modifyUser(String userId, String userName) {
		userManager.modifyUser(userId,userName);
	}
}
