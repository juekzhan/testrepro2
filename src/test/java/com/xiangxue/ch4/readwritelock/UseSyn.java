package com.xiangxue.ch4.readwritelock;

import com.xiangxue.ch1.SleepTools;

public class UseSyn implements GoodsService{

	private GoodsInfo goodsInfo;
	
	public UseSyn(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
    //
	@Override
	public synchronized GoodsInfo getNum() {
		SleepTools.secondS(5);
		return this.goodsInfo;
	}

	@Override
	public synchronized void setNum(int number) {
		SleepTools.secondS(5);
		goodsInfo.changeNumber(number);
	}
   
}
