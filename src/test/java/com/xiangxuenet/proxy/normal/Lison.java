package com.xiangxuenet.proxy.normal;

import com.xiangxuenet.proxy.IGetServer;

public class Lison {
      public static void main(String[] args) {
    	 /**
    	  * 自己不用代理去 问问题
    	  * */
//		IGetServer getServer = new Receptionist();
//		getServer.choice("御姐风");
		/**
		 * 通过代理去问 问题
		 */
		IGetServer juek = new JuekMan();
		juek.choice("喜欢的样子");
	}
}
