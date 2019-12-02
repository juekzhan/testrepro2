package com.xiangxue.ch5.queue;

import java.util.concurrent.DelayQueue;

public class PutOder implements Runnable {

	private DelayQueue<TtemVo<Order>> queue;

	public PutOder(DelayQueue<TtemVo<Order>> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		// 5秒后到期
		Order orderTb = new Order("Tb12345+8秒", 366);
		TtemVo<Order> itemTb = new TtemVo<Order>(8, orderTb);
		queue.offer(itemTb);
		System.out.println("订单8秒后超时：" + orderTb.getOderNo() + ";" + orderTb.getOrderMoney());
		// 8秒后到期
		Order orderJd = new Order("Jd54321+5秒", 788);
		TtemVo<Order> itemJd = new TtemVo<Order>(5, orderJd);
		queue.offer(itemJd);
		System.out.println("订单5秒后超时：" + orderJd.getOderNo() + ";" + orderJd.getOrderMoney());
	}

}
