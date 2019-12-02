package com.xiangxue.ch5.queue;

import java.util.concurrent.DelayQueue;

public class QueryOrder implements Runnable {
   
	private DelayQueue<TtemVo<Order>> queue;

	public QueryOrder(DelayQueue<TtemVo<Order>> queue) {
		super();
		this.queue = queue;
	}


	@Override
	public void run() {
		while(true) {
			try {
				TtemVo<Order> item = queue.take();
				Order order = (Order)item.getData();
				System.out.println("Get From Queue:"+"data="
				+order.getOderNo()+";"+order.getOrderMoney());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
