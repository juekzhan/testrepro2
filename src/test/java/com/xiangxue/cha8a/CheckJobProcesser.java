package com.xiangxue.cha8a;
 /* 任务完成后，在一定的时间供查询的结果
  * 之后为释放资源节约内存，需要定期处理过期 的任务
  * */

import java.util.Map;
import java.util.concurrent.DelayQueue;

import com.xiangxue.cha8a.vo.ItemVo;
import com.xiangxue.cha8a.vo.JobInfo;


public class CheckJobProcesser {
  //存放任务的队列
	private static DelayQueue<ItemVo<String>> queue  = new DelayQueue<>();
	
	private CheckJobProcesser() {}
	
	 private static class ProceessrHodeder{
		public static CheckJobProcesser jobProcesser = new CheckJobProcesser();
	}
	
	 public static CheckJobProcesser getInstance() {
		 return ProceessrHodeder.jobProcesser;
	 }
	 
	 /*处理队列中到期的任务*/
	 private static class FetchJob implements Runnable{
        private static DelayQueue<ItemVo<String>> queue = CheckJobProcesser.queue;
        
        private static Map<String,JobInfo<?>>  jobInfoMap = PendingJobPool.getMap();
        
		@Override
		public void run() {
			while(true) {
				try {
					ItemVo<String> item = queue.take();
					String jobName = item.getData();
					jobInfoMap.remove(jobName);
				    System.out.println(jobName + "过期了，从缓存中清除");
				}catch (Exception e) {
                 e.printStackTrace();
				}
			}
		}
		 
	 }
	 
	 /*任务完成后，放入队列，经过expireTime时间后，会从整个框架中移除*/
	    public void putJob(String jobName,long expireTime){
	        ItemVo<String> item = new ItemVo<String>(expireTime,jobName);
	        queue.offer(item);
	        System.out.println(jobName+"已经放入过期检查缓存，时长："+expireTime);
	    }
	    //结束吧   
	    static {
	    	Thread thread = new Thread(new FetchJob());
	    	thread.setDaemon(true);
	    	thread.start();
	    	System.out.println("开始检查守护线程.............");
	    }
}
