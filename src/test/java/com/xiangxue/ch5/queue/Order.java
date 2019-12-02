package com.xiangxue.ch5.queue;

public class Order {
	private final String oderNo;
	private final double orderMoney;

	public Order(String oderNo, double orderMoney) {
		super();
		this.oderNo = oderNo;
		this.orderMoney = orderMoney;
	}

	public String getOderNo() {
		return oderNo;
	}

	public double getOrderMoney() {
		return orderMoney;
	}
   
	
}
