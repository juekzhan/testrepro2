package com.juekjava.designPattern.command;
/**
 * 
 * @ClassName: GarageDoorOpenCommand  
 * @Package :com.juekjava.designPattern.command
 * @Description: 命令对象 把相应的对象 和对象的操作封装进去，然后命令对象只用执行相应的对象动作就行 
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年2月18日 上午11:39:49
 */
public class GarageDoorOffCommand implements ICommand {
	GarageDoor garageDoor;
	
	public  GarageDoorOffCommand(GarageDoor garageDoor) {
		this.garageDoor = garageDoor;
	}
	
	@Override
	public void execute() {
		garageDoor.close();
	}

}
