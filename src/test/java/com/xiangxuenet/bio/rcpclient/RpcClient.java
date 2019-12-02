package com.xiangxuenet.bio.rcpclient;


//类说明：rpc的客户端，调用远端服务
public class RpcClient {
 public static void main(String[] args) {
//	UserInfo  userInfo  = new UserInfo("juekzhan","xxxx@xx.com");
//	
//	 SendSms smsend = RpcClientFrame.getRemoteProxyObj(SendSms.class,"127.0.0.1", 9189);
//	
	//System.out.println(smsend.sendMail(userInfo));
	//Send
	 com.xiangxuenet.bio.rcpserver.StockService stockService = RpcClientFrame.getRemoteProxyObj(com.xiangxuenet.bio.rcpserver.StockService.class,"127.0.0.1",9190);
	 stockService.addStock("A001",1000);
     stockService.deduceStock("B002",50);
}
}
