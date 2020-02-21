package com.xiangxuenet.basenetty.serializable.protogenesis;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class UserInfo implements Serializable {

	/**
	 * 默认序列号
	 */
	private static final long serialVersionUID = 1L;
    
	
	private String userName;
	
	private int userId;
	
	public UserInfo buildUserName(String userName) {
		this.userName = userName;
		return this;
	}
	
	public UserInfo buildUserId(int userId) {
		this.userId = userId;
		return this;
	}

	public final String getUserName() {
		return userName;
	}

	public final void setUserName(String userName) {
		this.userName = userName;
	}

	public final int getUserId() {
		return userId;
	}

	public final void setUserId(int userId) {
		this.userId = userId;
	}
	
	//自行序列化
	public byte[] codeC() {
		ByteBuffer  buffer = ByteBuffer.allocate(1024);
		byte[] value = this.userName.getBytes();//userName转换为字节数组 value
		buffer.putInt(value.length);//写入字节数组value的长度
		buffer.put(value);//写入字节数组的value的值
		buffer.putInt(this.userId);//写入usrId的值
		buffer.flip();//准备读取buffer中的数据
		value = null;
		byte[] result = new byte[buffer.remaining()];
		buffer.get(result); //buffer中数据写入字节数组并作为结果返回
		return result;
	}
	
	//自行序列化方法2
	public byte[] codeC(ByteBuffer  buffer) {
		buffer.clear();
		byte[] value =  this.userName.getBytes();
		buffer.putInt(value.length);
		buffer.put(value);
		buffer.putInt(this.userId);
		buffer.flip();
		value = null;
		byte[] result  = new byte[buffer.remaining()];
		buffer.get(result);
		return result;
	}
}
