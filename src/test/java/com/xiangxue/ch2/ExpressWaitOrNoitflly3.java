package com.xiangxue.ch2;

/**
 * 
 * @ClassName: ExpressWaitOrNoitflly3  
 * @Package :com.xiangxue.ch2
 * @Description: 快递实体类  
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年4月29日 下午3:04:14
 */
public class ExpressWaitOrNoitflly3 {
    public final  static   String  CITY = "shanghai";

	private int km;/* 快递运输里程数 */
	private String site;/* 快递到达地点 */

	public ExpressWaitOrNoitflly3(int km, String site) {
		this.km = km;
		this.site = site;
	}

	public ExpressWaitOrNoitflly3() {
	}
    
	/*变化公里时 然后通知出于wait状态并需要处理公里数的线程进行业务处理*/
	public synchronized void changeKm() {
		this.km = 101;
		this.notifyAll();
	}
	//地点变换 然后通知其他处理
	public synchronized void changeSite() {
		this.site = "bingjing";
		this.notifyAll();  // 不会释放锁
	}
	//如果公里数 没有发生变换，那就等待 
	public synchronized void watiKm() {
		while(this.km <= 100){
			try {
				System.out.println("check km thread["+Thread.currentThread().getId()+"] is wait");
				this.wait();   //wait() 会释放锁
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//这个线程被唤醒
	  System.out.println("this KM is "+ this.km +",I will chage db");	
	}
	
	public synchronized void waitSite() {
		while(CITY.equals(this.site)) {
			try {
				System.out.println("check site thread["+Thread.currentThread().getId()+"] is wait");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//这个线程被唤醒
		  System.out.println("this site is "+ this.site +",I will chage db");
	}
}
