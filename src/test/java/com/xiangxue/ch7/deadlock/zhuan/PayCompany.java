package com.xiangxue.ch7.deadlock.zhuan;

import com.xiangxue.ch7.deadlock.zhuan.service.ITranfer;
import com.xiangxue.ch7.deadlock.zhuan.service.SafeTranseAccount;
import com.xiangxue.ch7.deadlock.zhuan.service.SafeTryLock;
import com.xiangxue.ch7.deadlock.zhuan.service.TrasnferAccount;

/**
 * 
 * @ClassName: PayCompany
 * @Package :com.xiangxue.ch7.deadlock.zhuan
 * @Description: 转账公司
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月19日 下午5:24:07
 */
@SuppressWarnings("unused")
public class PayCompany {
	
	private static class TransferThread extends Thread {

		private String name;

		private UserAccount from;

		private UserAccount to;

		private int amount;

		private ITranfer tranfer;

		public TransferThread(String name, UserAccount from, UserAccount to, int amount, ITranfer tranfer) {
			this.name = name;
			this.from = from;
			this.to = to;
			this.amount = amount;
			this.tranfer = tranfer;
		}

		public void run() {
			Thread.currentThread().setName(name);
			try {
				tranfer.transfer(from, to, amount);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {
        UserAccount zhangsan = new UserAccount("zhangsan",20000);
        UserAccount lisi = new UserAccount("lisi",20000);
        
//        ITranfer transfer = new TrasnferAccount();  //死锁1
     //   ITranfer transfer = new SafeTranseAccount();   //安全1
        ITranfer transfer = new SafeTryLock();   //安全2
        TransferThread tranfner11  = new TransferThread("张三到李四", zhangsan, lisi, 2000, transfer);
        TransferThread tranfner22 = new TransferThread("李四到张三", lisi, zhangsan, 2000, transfer);
        tranfner11.start();
       
        tranfner22.start();
	}
}
