package com.xiangxuenet.bio.rcpserver;

public class SmsRpcServer {
   public static void main(String[] args) {
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			try {
				RpcServerFrame serviceServer = new RpcServerFrame(9189);
				//下面的地方必须引用同一个包名的接口才行 才行
				serviceServer.registerService(com.xiangxuenet.bio.rcpclient.SendSms.class.getName(), SendSmsImpl.class);
				serviceServer.startService();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}).start();
}
}
