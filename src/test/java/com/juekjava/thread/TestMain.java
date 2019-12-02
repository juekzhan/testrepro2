package com.juekjava.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 
 * @ClassName: TestMain  
 * @Package :com.xiangxue
 * @Description: 线程控制类
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2013年4月22日 下午7:30:24
 */
public class TestMain {
  public static void main(String[] args) {
	  //虚拟机线程管理的接口
	ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
	//返回具有堆栈跟踪和同步信息的所有活动线程的线程信息。
	//https://docs.oracle.com/javase/8/docs/api/java/lang/management/ThreadMXBean.html
	ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
	
	for(ThreadInfo  threadInfo : threadInfos) {
		System.out.println("["+threadInfo.getThreadId()+"]" + " " +threadInfo.getThreadName() );
	}
	System.out.println("mmmmmmm");
	//[4] Signal Dispatcher 分发处理 发送给虚拟机信号的线程
	//[2] Reference Handler 清除引用
	//[3] Finalizer  垃圾回收的机制 
	//[5] Attach Listener  获取当前运行的信息
 }
}
