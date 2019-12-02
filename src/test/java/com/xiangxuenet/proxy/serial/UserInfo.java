package com.xiangxuenet.proxy.serial;

import java.io.Serializable;

public class UserInfo implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 2918665037004577482L;
	private final String name;
	private final String phone;
	public UserInfo(String name, String phone) {
	        this.name = name;
	        this.phone = phone;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getPhone() {
	        return phone;
	    }
}
