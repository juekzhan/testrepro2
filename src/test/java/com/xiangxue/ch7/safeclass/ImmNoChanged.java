package com.xiangxue.ch7.safeclass;

import java.util.ArrayList;
import java.util.List;

import net.jcip.annotations.ThreadSafe;

/**
 * 
 * @ClassName: ImmNoChanged  
 * @Package :com.xiangxue.ch7.safeclass
 * @Description: 累不可变，线程安全
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年7月19日 下午1:53:38
 */

@ThreadSafe
public class ImmNoChanged {
  private final List<Integer> list = new ArrayList<>();   //成员变量  不向外发布
  
  public ImmNoChanged() {
	  list.add(1);
	  list.add(2);
	  list.add(3);
  }
  
  public boolean isContain(int i) {
	  return list.contains(i);
  }
}
