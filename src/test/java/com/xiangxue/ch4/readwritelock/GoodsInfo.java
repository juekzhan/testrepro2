package com.xiangxue.ch4.readwritelock;

public class GoodsInfo {
	private String name;
	private double totalMoney;// 总销售额
	private int storeNumber;// 库存数
    
	public GoodsInfo(String name, double totalMoney, int storeNumber) {
		this.name = name;
		this.totalMoney = totalMoney;
		this.storeNumber = storeNumber;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(int storeNumber) {
		this.storeNumber = storeNumber;
	}

	public String getName() {
		return name;
	}
    
	  public void changeNumber(int sellNumber){
	        this.totalMoney += sellNumber*25;
	        this.storeNumber -= sellNumber;
	    }
}
