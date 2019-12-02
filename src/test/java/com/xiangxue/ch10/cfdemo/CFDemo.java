package com.xiangxue.ch10.cfdemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.xiangxue.ch1.SleepTools;

//类说明：CompletableFuture使用范例

public class CFDemo {
 static class GetResult  extends Thread{
	 private CompletableFuture<Integer> f;
    
	public GetResult(String threadName,CompletableFuture<Integer> f) {
		super(threadName);
		this.f = f;
	}

	@Override
	public void run() {
			try {
				System.out.println("waiting  result ......");
				System.out.println(this.getName() + ":" + f.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
 }
 
 
 public static void main(String[] args) throws Exception {
	final CompletableFuture<Integer> f = new CompletableFuture<Integer>();
	
	new GetResult("Client1", f).start();
	new GetResult("Client2", f).start();
	
	System.out.println("sleeping");
	SleepTools.second(2);
	f.complete(100);
	//f.completeExceptionally(new Exception());
	System.in.read();
}
}

