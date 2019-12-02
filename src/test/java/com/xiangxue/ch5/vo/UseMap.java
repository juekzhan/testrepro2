package com.xiangxue.ch5.vo;

import java.util.concurrent.ConcurrentHashMap;

public class UseMap {
 public static void main(String[] args) {
	ConcurrentHashMap<KeyVo, String> map =  new ConcurrentHashMap<>();
	
	KeyVo keyVo = new KeyVo(1, "A");
	
	System.out.println("put不存在的值--------");
	System.out.println(map.put(keyVo, "AAA"));
	System.out.println(map.get(keyVo));
	
	System.out.println("put 已经存在的值--------");
	System.out.println(map.put(keyVo, "BBB"));
	System.out.println(map.get(keyVo));
	
	System.out.println("putIfAbsent已存在的key的值------");
	System.out.println(map.putIfAbsent(keyVo, "CCC"));    // putIfAbsent() 如果对应的key已经存在 是放不进去的
	System.out.println(map.get(keyVo));
	
	System.out.println("putIfAbsent不存在的key的值------");
	KeyVo keyVo2 = new KeyVo(2, "B");
	System.out.println(map.putIfAbsent(keyVo2,"CCC"));
    System.out.println(map.get(keyVo2));
}
}
