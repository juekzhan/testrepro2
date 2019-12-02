package com.xiangxue.ch7.safeclass.safepublish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @ClassName: SafePublishList  
 * @Package :com.xiangxue.ch7.safeclass.safepublish
 * @Description: 安全发布
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月19日 下午2:24:14
 */
public class SafePublishList {
   private List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>(3));
   
   public SafePublishList() {
		  list.add(1);
		  list.add(2);
		  list.add(3);
		  
	  }
	   
	  public List<Integer> getList() {
		  return list;
	  }
	  
	  public static void main(String[] args) {
		  SafePublishList safePublishList = new SafePublishList();
			List<Integer> list  = safePublishList.getList();
			System.out.println(list);
			list.add(4);
			System.out.println(list);
			System.out.println(safePublishList.getList());
		}
}
