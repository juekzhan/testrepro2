package com.xiangxue.ch4.readwritelock;

import java.util.Random;

import com.xiangxue.ch1.SleepTools;


public class TestMainReadWrite {
	static final int readWriteRatio = 10;//读写线程的比例
    static final int minthreadCount = 3;//最少线程数
    
    private static class GetThread implements Runnable{
    	
        private GoodsService goodsService;
    	
		public GetThread(GoodsService goodsService) {
			this.goodsService = goodsService;
		}

		@Override
		public void run() {
			long start = System.currentTimeMillis();
			for(int i = 0 ;i< 100;i++) {
				goodsService.getNum();
			}
			System.out.println(Thread.currentThread().getName() + "读取商品用的时间" + (System.currentTimeMillis() -start) +"ms");
		}
    	
    }
    
    private static class SetThread implements Runnable{
       
    	 private GoodsService goodsService;
     	
 		public SetThread(GoodsService goodsService) {
 			this.goodsService = goodsService;
 		}
    	
		@Override
		public void run() {
			long start = System.currentTimeMillis();
			Random random = new Random();
			for(int i = 0; i<10; i++) {
				goodsService.setNum(random.nextInt(10));
			}
			System.out.println(Thread.currentThread().getName() + "写-》商品用的时间" + (System.currentTimeMillis() -start) +"ms");
		}
    }
    
    public static void main(String[] args) {
    	GoodsInfo goodsInfo = new GoodsInfo("Cup",100000,10000);
		//GoodsService goodsService = new UseSyn(goodsInfo);
		GoodsService goodsService = new UseReadWriteLock(goodsInfo);
	     for(int i = 0;i<minthreadCount;i++){
	            Thread setT = new Thread(new SetThread(goodsService));
	            for(int j=0;j<readWriteRatio;j++) {
	                Thread getT = new Thread(new GetThread(goodsService));
	                getT.start();           	
	            }
	            SleepTools.secondS(50);
	            setT.start();
	        }
	}
}
