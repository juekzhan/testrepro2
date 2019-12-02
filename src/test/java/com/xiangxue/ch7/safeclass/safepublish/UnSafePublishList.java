package com.xiangxue.ch7.safeclass.safepublish;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: UnSafePublish  
 * @Package :com.xiangxue.ch7.safeclass.safepublish
 * @Description: 不 安全的发布
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月19日 下午2:12:23
 */
public class UnSafePublishList {
  private List<Integer> list = new ArrayList<>(3);    //因为list是接口 当于指针
  
  public UnSafePublishList() {
	  list.add(1);
	  list.add(2);
	  list.add(3);
	  
  }
   
  public List<Integer> getList() {
	  return list;
  }
  
  public static void main(String[] args) {
	UnSafePublishList unSafePublish = new UnSafePublishList();
	List<Integer> list  = unSafePublish.getList();
	System.out.println(list);
	list.add(4);
	System.out.println(list);
	System.out.println(unSafePublish.getList());
}
}
