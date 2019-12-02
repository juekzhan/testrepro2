package com.juekjava.designPattern.command;

public class LightOnCommand implements ICommand {
    Light light;
	
     public LightOnCommand(Light light) {
        this.light = light;
     }
    
	@Override
	public void execute() {
		light.on();
	}
  
}
