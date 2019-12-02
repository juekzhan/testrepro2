package com.xiangxue.ch2.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 
 * @ClassName: ForkJoinFiles  
 * @Package :com.xiangxue.ch2.forkjoin
 * @Description: 不返回值的话用 Action 任务队列
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年5月5日 下午6:39:13
 */
public class ForkJoinFiles  extends RecursiveAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -479314150937079845L;
	private File path;
	
	

	public ForkJoinFiles(File path) {
		this.path = path;
	}



	@Override
	protected void compute() {
		List<ForkJoinFiles> subTasks = new ArrayList<>();
		
		File[] files = path.listFiles();
		
		if(files != null) {
			for(File file : files) {
				if(file.isDirectory()) {
					//对每个子目录都新建一个子任务
					subTasks.add(new ForkJoinFiles(file));
				}else {
					//if(file.getAbsolutePath().endsWith(".txt")) {
						System.out.println("文件" + file.getAbsolutePath());
					//}
				}
			}
			if(!subTasks.isEmpty()) {
				 // 在当前的 ForkJoinPool 上调度所有的子任务。
				for(ForkJoinFiles forkJoinFiles : invokeAll(subTasks)) {
					forkJoinFiles.join();
				}
			}
		}
		
	}
  
	
	public static void main(String[] args) {
		try {
			ForkJoinPool pool = new ForkJoinPool();
			ForkJoinFiles task = new ForkJoinFiles(new File("D:\\"));
			//ConcurrentHashMap<K, V>
			//异步提交
			pool.execute(task);
			
			System.out.println("Task is Running .......");
			Thread.sleep(1);
			int otherWork = 0;
			
			for(int i=0;i<100;i++){
                otherWork = otherWork+i;
            }
            System.out.println("Main Thread done sth......,otherWork="
                    +otherWork);
			
			task.join();//阻塞方法
			System.out.println("Task End");
		}catch (Exception e) {
          e.printStackTrace();
		}
	}
}
