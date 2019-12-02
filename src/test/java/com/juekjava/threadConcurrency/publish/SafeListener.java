package com.juekjava.threadConcurrency.publish;
/**
 * 
 * @ClassName: SafeListener  
 * @Package :com.juekjava.threadConcurrency.publish
 * @Description:使用工厂方法来防止this 引用在构造过程中逸出
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2018年12月5日 下午7:56:09
 */

import java.util.EventListener;



public class SafeListener {
	@SuppressWarnings("unused")
	private final EventListener listener;

	private SafeListener() {
		listener = new EventListener() {
			//
			//@SuppressWarnings("unused")
//			public void onEvent(Event e) {
//				// doSomthing(e);
//			}
		};
	}
    
	
}
