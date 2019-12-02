package com.xiangxuenet.proxy.statics;

public interface UserManager {
  public void addUser(String userId,String userName);
  
  public void delUser(String userId);
  
  public void findUser(String userId);
  
  public void modifyUser(String userId ,String userName); 
	  
}
