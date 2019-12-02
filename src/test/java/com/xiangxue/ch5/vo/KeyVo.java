package com.xiangxue.ch5.vo;

import java.util.Objects;
/**
 * 
 * @ClassName: KeyVo  
 * @Package :com.xiangxue.ch5.vo
 * @Description: 判断对象中的属性是否相等 
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月23日 下午4:24:15
 */
public class KeyVo {
	private final int id;

	private final String name;

	public KeyVo(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		KeyVo keyVo = (KeyVo) obj;
		return id == keyVo.id && Objects.equals(name, keyVo.name);
	}

}
