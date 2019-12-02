package com.xiangxuenet.bio.rcpserver;

import java.io.Serializable;

public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -340553129843346810L;
    
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
