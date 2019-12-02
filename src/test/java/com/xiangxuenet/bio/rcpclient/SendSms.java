package com.xiangxuenet.bio.rcpclient;

import com.xiangxuenet.bio.rcpserver.UserInfo;

//短信发送接口
public interface SendSms {
	
	boolean sendMail(UserInfo user);
}
