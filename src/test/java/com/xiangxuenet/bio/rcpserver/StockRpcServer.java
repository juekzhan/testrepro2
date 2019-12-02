package com.xiangxuenet.bio.rcpserver;


public class StockRpcServer {

	public static void main(String[] args) {
      new Thread(new Runnable() {
		@Override
		public void run() {
         try {
        	 RpcServerFrame rpcServerFrame = new RpcServerFrame(9190);
        	 rpcServerFrame.registerService(StockService.class.getName(), StockServiceImpl.class);
        	 rpcServerFrame.startService();
         }catch(Exception e) {
        	 e.printStackTrace();
         }
		}
	}).start();
	}

}
