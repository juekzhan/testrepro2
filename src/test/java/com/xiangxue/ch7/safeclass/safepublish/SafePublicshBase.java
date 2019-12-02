package com.xiangxue.ch7.safeclass.safepublish;

/**
 * 
 * @ClassName: SafePublicsh
 * @Package :com.xiangxue.ch7.safeclass.safepublish
 * @Description: 发布。类的发布   基本类型的发布
 * @Author: shuling.zhan 
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月19日 下午2:06:29
 */
public class SafePublicshBase {
	private int i;

	public SafePublicshBase() {
		i = 2;
	}

	public int getI() {
		return i;
	}

	public static void main(String[] args) {
		SafePublicshBase safePublicsh = new SafePublicshBase();
		int j = safePublicsh.getI();
		System.out.println("before j = " + j);
		j = 3;
		System.out.println("after j = " + j);
		System.out.println("getI = " + safePublicsh.getI());
	}
}
