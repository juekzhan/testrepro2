package com.xiangxuenet.bio.local;

import com.xiangxuenet.bio.rcpserver.NormalBusi;
import com.xiangxuenet.bio.rcpserver.SendSms;
import com.xiangxuenet.bio.rcpserver.SendSmsImpl;
import com.xiangxuenet.bio.rcpserver.StockService;
import com.xiangxuenet.bio.rcpserver.StockServiceImpl;
import com.xiangxuenet.bio.rcpserver.UserInfo;

public class ThreadCall {

	public static void main(String[] args) {
		//其他业务工作
        NormalBusi normalBusi = new NormalBusi();
        normalBusi.business();
        new Thread(new StockTask()).start();
        new Thread(new SmsTask()).start();
	}
     
	
	 private static class StockTask implements Runnable{

	        @Override
	        public void run() {
	            StockService stockService = new StockServiceImpl();
	            stockService.addStock("A001",1000);
	            stockService.deduceStock("B002",50);
	        }
	    }

	    private static class SmsTask implements Runnable{

	        @Override
	        public void run() {
	            SendSms sendSms = new SendSmsImpl();
	            UserInfo userInfo
	                    = new UserInfo("Mark","Mark@xiangxue.com");
	            System.out.println("Send mail: "+ sendSms.sendMail(userInfo));
	        }
	    }
}
