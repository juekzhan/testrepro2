package com.xiangxuenet.bio.local;

import com.xiangxuenet.bio.rcpserver.NormalBusi;
import com.xiangxuenet.bio.rcpserver.SendSms;
import com.xiangxuenet.bio.rcpserver.SendSmsImpl;
import com.xiangxuenet.bio.rcpserver.StockService;
import com.xiangxuenet.bio.rcpserver.StockServiceImpl;
import com.xiangxuenet.bio.rcpserver.UserInfo;

public class LocalCall {

	public static void main(String[] args) {
		   NormalBusi normalBusi = new NormalBusi();
	        normalBusi.business();

	        StockService stockService = new StockServiceImpl();
	        stockService.addStock("A001",1000);
	        stockService.deduceStock("B002",50);
             
	        SendSms sendSms = new SendSmsImpl();
	        UserInfo userInfo = new UserInfo("Mark","Mark@xiangxue.com");
	        System.out.println("Send mail: "+ sendSms.sendMail(userInfo));
	}

}
