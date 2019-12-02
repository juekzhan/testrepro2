package com.xiangxue.ch4.readwritelock;



/**
 * 
 * @ClassName: GoodsService  
 * @Package :com.xiangxue.ch4.readwritelock
 * @Description: 商品服务
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年5月21日 下午4:54:59
 */
public interface GoodsService {
	public GoodsInfo getNum();//获得商品的信息
	public void setNum(int number);//设置商品的数量
}
