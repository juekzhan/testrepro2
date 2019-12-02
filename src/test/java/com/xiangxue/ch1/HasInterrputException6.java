package com.xiangxue.ch1;

public class HasInterrputException6 {
    private static class  HasInterrputInside extends Thread{
    	public void run() {
    		System.out.println(Thread.currentThread().getName() +"first-> flag isInterrput =:" + isInterrupted());
    		while(!isInterrupted()) {
    			try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					//如果中断捕获到 异常信息，那么中断标志还会被置为 false
					System.out.println(Thread.currentThread().getName() +"exception-> flag isInterrput =:" + isInterrupted());
					e.printStackTrace();
					//可以再次原型 interrupt()
					//interrupt();
				}
    			System.out.println(Thread.currentThread().getName() +"run-> flag isInterrput =:" + isInterrupted());
    		
    		}
    		System.out.println(Thread.currentThread().getName() +"last-> flag isInterrput =:" + isInterrupted());
    	}
    }
    
    public static void main(String[] args) throws Exception{
    	HasInterrputInside side = new HasInterrputInside();
    	side.start();
    	Thread.sleep(1000);
    	side.interrupt();
	}
} 
