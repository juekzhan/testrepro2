package com.xiangxuenet.bio.rcpserver;
//变动库存服务
public interface StockService {
	 void addStock(String goodsId, int addAmout);
	 void deduceStock(String goodsId, int deduceAmout);
}
