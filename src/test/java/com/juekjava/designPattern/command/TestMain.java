package com.juekjava.designPattern.command;

public class TestMain {
  public static void main(String[] args) {
	SimpleRemoteControl remoteControl = new SimpleRemoteControl();
	Light light = new Light();
	LightOnCommand lightOnCommand = new LightOnCommand(light);
	LightOffCommand lightOffCommand = new LightOffCommand(light);
	
	
	GarageDoor garageDoor = new GarageDoor();
	GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);
	GarageDoorOffCommand garageDoorOffCommand = new GarageDoorOffCommand(garageDoor);
	
	remoteControl.setCommand(1, lightOnCommand, lightOffCommand);
	remoteControl.setCommand(2, garageDoorOpenCommand, garageDoorOffCommand);
	System.out.println(remoteControl);
	
	remoteControl.onButtonWasPushed(1);
	remoteControl.offButtonWasPushed(1);
	remoteControl.onButtonWasPushed(2);
	remoteControl.offButtonWasPushed(2);
}
}
