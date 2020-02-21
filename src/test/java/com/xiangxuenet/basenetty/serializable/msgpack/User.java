package com.xiangxuenet.basenetty.serializable.msgpack;

import org.msgpack.annotation.Message;

@Message // MessagePack提供的注解，表明这是一个需要序列化的实体类
public class User {
	private String id;

	private String userName;

	private int age;

	private UserContact userContact;

	public User(String id, String userName, int age) {
		this.id = id;
		this.userName = userName;
		this.age = age;
	}

	public User() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public UserContact getUserContact() {
		return userContact;
	}

	public void setUserContact(UserContact userContact) {
		this.userContact = userContact;
	}

	@Override
	public String toString() {
		return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", userContact=" + userContact +
                '}';
	}
    
	
}
