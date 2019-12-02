package com.juekjava.thread;

public class RunnableEnd {
  private static class RunableThread implements Runnable{

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		while(!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().isInterrupted()+" Exception~!");
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
			System.out.println(threadName + "is run!");
		}
		System.out.println(threadName+" interrput flag is "
				+Thread.currentThread().isInterrupted());
	}
	  
  }
  
  @SuppressWarnings("static-access")
public static void main(String[] args) throws Exception {
	RunableThread runableThread = new RunableThread();
	Thread useThread = new Thread(runableThread,"endThread");
	useThread.start();
	useThread.sleep(20);
	useThread.interrupt();
}
}
