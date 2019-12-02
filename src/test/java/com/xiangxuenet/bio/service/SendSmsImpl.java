package com.xiangxuenet.bio.service;

import com.xiangxuenet.bio.rcpserver.UserInfo;

public class SendSmsImpl implements SendSms {

	@Override
	public boolean sendMail(UserInfo user) {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("已发送短信息给："+user.getName()+"到【"+user.getPhone()+"】XXXXXXXXXXXXXXXX");
	     return true;
	}
  
}
