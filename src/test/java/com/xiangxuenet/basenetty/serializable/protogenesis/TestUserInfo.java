package com.xiangxuenet.basenetty.serializable.protogenesis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
//**JDK  序列化 和 自定义序列化对比 
public class TestUserInfo {
 public static void main(String[] args) throws IOException {
	UserInfo info  = new UserInfo();
	info.buildUserId(100).buildUserName("Welcome to Netty");
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	ObjectOutputStream os = new ObjectOutputStream(bos);
	os.writeObject(info);
	os.flush();
	os.close();
	byte[] b  = bos.toByteArray();
	System.out.println("The jdk serializable length is:"  + b.length);
	bos.close();
	System.out.println("-----------------------------------");
	System.out.println("The byte array serializable length  is :"+ info.codeC().length);
}
}
