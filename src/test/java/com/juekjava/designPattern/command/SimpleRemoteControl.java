package com.juekjava.designPattern.command;

public class SimpleRemoteControl {
  ICommand[] commandOns;
  ICommand[] commandOffs;
  
  public SimpleRemoteControl() {
	  commandOns = new ICommand[7];
	  commandOffs = new ICommand[7];
	  
	  ICommand noCommand = new NoCommand();
	  for(int i = 0 ;i < 7;i++) {
		  commandOns[i] = noCommand; 
		  commandOffs[i] = noCommand; 
	  }
  }
  
  public void setCommand(int slot ,ICommand commandOn , ICommand commandOff) {
	  commandOns[slot] = commandOn;
	  commandOffs[slot] = commandOff;
  }
  
  public void onButtonWasPushed(int slot) {
	  commandOns[slot].execute();
  }
  
  public void offButtonWasPushed(int slot) {
	  commandOffs[slot].execute();
  }
  
  public String toString() {
	  StringBuffer  stringBuffer = new StringBuffer();
	  stringBuffer.append("\n--------------Remote Control ------\n");
       for(int i = 0 ;i< commandOffs.length; i++) {
    	   stringBuffer.append("[slot"+ i +"]" + commandOns[i].getClass().getName() + "     "
    			   + commandOffs[i].getClass().getName() + "\n");
       }
	  return stringBuffer.toString();
  }
}
