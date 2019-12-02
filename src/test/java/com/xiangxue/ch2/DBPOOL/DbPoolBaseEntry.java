package com.xiangxue.ch2.DBPOOL;
/**
 * 
 * @ClassName: DbPoolBaseEntry  
 * @Package :com.xiangxue.DBPOOL
 * @Description: 数据库连接池 实例
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年4月30日 上午9:40:37
 */

import java.sql.Connection;
import java.util.LinkedList;

public class DbPoolBaseEntry {
 
  //存放连接池 的容器
  private static LinkedList<Connection> pool = new LinkedList<Connection>();
  
  //初始化连接数 可传入参数
  public DbPoolBaseEntry(int initSize) {
	  if(initSize > 0) {
		  if(!pool.isEmpty()) pool.clear();
		  for(int i =0 ; i<initSize;i++) {
			  pool.addLast(SqlConnectImpl.fetchConnection());
		  }
	  }
  }
  
  //释放链接
  public void releaseConnection(Connection connection) {
	  if(connection != null) {
		  synchronized (pool) {
			pool.addLast(connection);
			//通知其他 用对象的线程  ，唤醒他们去拿链接
			pool.notifyAll();
		}
	  }
  }
  
  //获取链接  //参数为等待时间
  public Connection fetchConnection(long mills) throws InterruptedException{
	  synchronized (pool) {
		  //永不超时
		if(mills <= 0) {
			while(pool.isEmpty()) {  //如果池子为null  就一直等待
				pool.wait();
			}
			return pool.removeFirst();   //如果唤醒对象，就返回线程池的第一个链接
		}
		// 有超时时间的话
		if(mills > 0) {   //
			long future = System.currentTimeMillis() + mills;  //超时 时刻
			//等待时长
			long remaining = mills;
			while(pool.isEmpty() && remaining > 0) {  //若果池子为null 并且还没有超时
				pool.wait(remaining);
				//唤醒一次重新计算时长~！ 中间 还剩下多少时间
				remaining = future - System.currentTimeMillis();
			}
			Connection connection = null;
			if(!pool.isEmpty()) {
				connection = pool.removeFirst();
			}
			return connection;
		}
		return null;
	}
  }
}
