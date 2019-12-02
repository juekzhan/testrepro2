package com.xiangxuenet.bio.rcpserver;

import java.util.concurrent.ConcurrentHashMap;

public class StockServiceImpl implements StockService {
    private static ConcurrentHashMap<String, Integer> goodsData
                    = new ConcurrentHashMap<String, Integer>();
	
	
    static {
    	goodsData.put("A001", 1000);
    	goodsData.put("B001", 2000);
    	goodsData.put("C001", 3000);
    	goodsData.put("D001", 4000);
    }
    
	@Override
	public synchronized void addStock(String goodsId, int addAmout) {
		    System.out.println("+++++++++++++++++增加商品："+goodsId+"的库存,数量为："+addAmout);
	        int amount = goodsData.get(goodsId)+addAmout;
	        goodsData.put(goodsId,amount);
	        System.out.println("+++++++++++++++++商品："+goodsId+"的库存,数量变为："+amount);		
	}

	@Override
	public synchronized void deduceStock(String goodsId, int deduceAmout) {
		System.out.println("-------------------减少商品："+goodsId+"的库存,数量为："+ deduceAmout);
        int amount = goodsData.get(goodsId)- deduceAmout;
        goodsData.put(goodsId,amount);
	}

}
