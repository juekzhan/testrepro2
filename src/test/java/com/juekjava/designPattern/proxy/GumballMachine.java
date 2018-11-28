package com.juekjava.designPattern.proxy;

import lombok.Data;

/**
 * 糖果机器
 * @author zsh12489
 *
 */
@Data
public class GumballMachine {
	//位置用 Sting
     String  location;
     //数量
     int count;
      
     String state;
     
	

	public GumballMachine(String location, int count) {
		super();
		this.location = location;
		this.count = count;
	}
 
}
