package com.xiangxue.ch1;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 
 * @ClassName: OnlyMain1  
 * @Package :com.xiangxue.ch1
 * @Description: 线程监控接口
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 */
public class OnlyMain1 {
  public static void main(String[] args) {
	  //线程管理接口
	ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
	// 返回具有堆栈跟踪和同步信息的所有活动线程的线程信息 当此方法返回时，返回的数组中包含的某些线程可能已终止
	ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
	for(ThreadInfo threadInfo : threadInfos) {
		System.out.println("["+threadInfo.getThreadId()+"]" + " " +threadInfo.getThreadName());
	}
  }
}
