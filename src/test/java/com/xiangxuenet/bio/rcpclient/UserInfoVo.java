package com.xiangxuenet.bio.rcpclient;

import java.io.Serializable;

public class UserInfoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1135478866969050700L;
    
    private final String name;
    private final String phone;

    public UserInfoVo(String name, String phone) {
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
