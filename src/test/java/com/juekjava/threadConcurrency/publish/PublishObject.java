package com.juekjava.threadConcurrency.publish;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @ClassName: PublishObject  
 * @Package :com.juekjava.threadConcurrency.publish
 * @Description: 发布对象的Set
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2018年12月5日 下午7:42:00
 */
public class PublishObject {
  public static Set<PojoObject> kownObject;
  
  public void initialize() {
	  kownObject = new HashSet<>();
  }
}
