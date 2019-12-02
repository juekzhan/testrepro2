package com.xiangxueJVM.ch5;

import java.util.Map;

// VM Args：-Xms20m -Xmx20m -Xmn2m -XX:+PrintGC
//如果无法连接服务器，也可以使用java.lang.Thread类的getAllStackTraces（）方法用于获取
public class AllStackTraces {
  @SuppressWarnings("unused")
public static void main(String[] args) {
	while(true) {
		byte[] b = null;
		for(int i = 0;i<10;i++) {
			b = new byte[1*1024*1024];
		}
		try {
			Thread.sleep(1000);
		}catch (Exception e) {
           e.printStackTrace();
		}
		 //于获取虚拟机中所有线程的StackTraceElement对象
		 Map<Thread,StackTraceElement[]> threadMap = Thread.getAllStackTraces();
		 for(Map.Entry<Thread,StackTraceElement[]> entry:threadMap.entrySet()){
             Thread t = entry.getKey();
             StackTraceElement[] ss = entry.getValue();
             //打印线程信息
             System.out.println(t.getName()+"-"+t.getId());
             for (StackTraceElement s: ss){ //打印线程详情信息
                 System.out.println(s);
             }
         }
	}
}
}
