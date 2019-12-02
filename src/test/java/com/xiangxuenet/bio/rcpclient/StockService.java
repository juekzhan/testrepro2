package com.xiangxuenet.bio.rcpclient;
//变动库存的服务接口
public interface StockService {
  void addStock(String goodsId,int addAmount);
  
  void deduceStock(String goodsId,int deduceAmount);
}
