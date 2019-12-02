package com.juekjava.threadConcurrency;

import java.util.Date;

public class TestMain {

	public static void main(String[] args) {
		new Date();
		ServerImpl se = new ServerImpl();
		se.setiSequeeue(new Sequence());
      for(int i = 0 ; i< 4; i++) {
		Thread thread = new Thread(new Runnable() {
             
			@Override
			public void run() {
				int value = 120;
				while (value > 0) {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					value--;
					System.out.println("非原子操作：" + se.diaoYong() + "的值        原子操作：" + "子线程" + Thread.currentThread().getName() + "的输出");
				}
			}

		});
		//thread.setDaemon(true);
		thread.start();
		//Vector<?> v = new Vector<>();
		//S
      }
      try {
		//Thread.sleep(3000);
    	  System.out.println(new Date());
	} catch (Exception e) {
		e.printStackTrace();
	}
	 System.out.println("主线程"+Thread.currentThread().getName()+"运行完毕~！~！");
	}

}
