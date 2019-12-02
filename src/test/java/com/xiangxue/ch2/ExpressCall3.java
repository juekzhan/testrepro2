package com.xiangxue.ch2;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @ClassName: ExpressCall3  
 * @Package :com.xiangxue.ch2
 * @Description: 线程的 监听模式
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年4月29日 下午3:56:30
 */
public class ExpressCall3 {
  private static ExpressWaitOrNoitflly3 express = new ExpressWaitOrNoitflly3(0, ExpressWaitOrNoitflly3.CITY);
  
  /**
   * 检查里程线程 是否变化 不满足条件 线程一直等待
   */
  
  private static class Checkkm extends Thread{
	  public void run() {
		 new ConcurrentHashMap<>().size();
		  express.watiKm();
	  }
  }
  
  private static class CheckSite extends Thread{
	  public void run() {
		  express.waitSite();
	  }
  }
  
  public static void main(String[] args) throws Exception {
	for(int i=0; i< 3; i++) {
		new Checkkm().start();
	}
	
	for(int i = 0 ;i<3;i++) {
		new CheckSite().start();
	}
	
	Thread.sleep(5000);
	System.out.println("call KM  OR Site is changed");
	express.changeKm();
	express.changeSite();
}
}
