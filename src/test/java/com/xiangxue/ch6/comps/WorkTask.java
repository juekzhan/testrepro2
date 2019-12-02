package com.xiangxue.ch6.comps;

import java.util.Random;
import java.util.concurrent.Callable;

public class WorkTask implements Callable<Integer> {
    
	@SuppressWarnings("unused")
	private String nameThread = "";

	public WorkTask(String name) {
		this.nameThread = name;
	}


	@Override
	public Integer call(){
		int sleepTime = new Random().nextInt(100);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return sleepTime;
	}
}
