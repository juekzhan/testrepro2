package com.juekjava.designPattern.proxy;

public class TestMain {
     
	public static void main(String[] args) {
      int count = 10;
      String location = "shanghai";
    
      
      GumballMachine machine = new GumballMachine(location, count);
      
      GumballMonitor monitor = new GumballMonitor(machine);
      
      monitor.report();
	}

}
