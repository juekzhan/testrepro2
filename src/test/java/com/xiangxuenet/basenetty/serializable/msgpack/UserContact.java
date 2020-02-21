package com.xiangxuenet.basenetty.serializable.msgpack;

import org.msgpack.annotation.Message;

@Message // MessagePack提供的注解，表明这是一个需要序列化的实体类
public class UserContact {
	private String mail;

	private String phone;

	public UserContact() {

	}

	public UserContact(String mail, String phone) {
		this.mail = mail;
		this.phone = phone;
	}

	public final String getMail() {
		return mail;
	}

	public final void setMail(String mail) {
		this.mail = mail;
	}

	public final String getPhone() {
		return phone;
	}

	public final void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserContact{ mail = '" + mail + "phone" + phone +"}";
	}

}
