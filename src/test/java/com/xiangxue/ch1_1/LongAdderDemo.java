package com.xiangxue.ch1_1;
// LongAdder 性能 分析

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {
	private static final int MAX_THREADS = 20;

	private static final int TASK_COUNT = 400;

	private static final int TARGET_COUNT = 900000000;

	private AtomicLong account = new AtomicLong(0L);

	private LongAdder laccount = new LongAdder();

	private long count = 0;

	private static CountDownLatch cdlsync = new CountDownLatch(TASK_COUNT);
	private static CountDownLatch cdlatiomic = new CountDownLatch(TASK_COUNT);
	private static CountDownLatch cdladder = new CountDownLatch(TASK_COUNT);

	protected synchronized long inc() {
		return ++count;
	}

	protected synchronized long getCount() {
		return count;
	}

	public class SynTask implements Runnable {
		protected String name;
		protected long starttime;
		LongAdderDemo out;

		public SynTask(long starttime, LongAdderDemo out) {
			this.starttime = starttime;
			this.out = out;
		}

		@Override
		public void run() {
			long v = out.getCount();
			while (v < TARGET_COUNT) {
				v = out.inc();
			}
			long endtime = System.currentTimeMillis();
			System.out.println("SyncTask spend:" + (endtime - starttime) + "ms");
			cdlsync.countDown();
		}

	}

	// 执行
	public void testSync() throws InterruptedException {
		ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
		long starttime = System.currentTimeMillis();
		SynTask sync = new SynTask(starttime, this);
		for (int i = 0; i < TASK_COUNT; i++) {
			exe.submit(sync);
		}
		cdlsync.await();
		exe.shutdown();
	}

	// 原子性执行的任务
	public class AtomicTask implements Runnable {
		protected String name;
		protected long starttime;

		public AtomicTask(long starttime) {
			this.starttime = starttime;
		}

		@Override
		public void run() {
			long v = account.get();
			while (v < TARGET_COUNT) {
				v = account.incrementAndGet();
			}
			long endtime = System.currentTimeMillis();
			System.out.println("AtomicTask spend:" + (endtime - starttime) + "ms");
			cdlatiomic.countDown();
		}
	}
	
	

    /*原子型long的执行测试*/
    public void testAtomic() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        AtomicTask atomic = new AtomicTask(starttime);
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(atomic);
        }
        cdlatiomic.await();
        exe.shutdown();
    } 
	
    /*LongAdder的测试任务*/
    public class LongAdderTask implements Runnable {
        protected String name;
        protected long startTime;

        public LongAdderTask(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = laccount.sum();
            while (v < TARGET_COUNT) {
            	laccount.increment();
                v = laccount.sum();
            }
            long endtime = System.currentTimeMillis();
            System.out.println("LongAdderTask spend:" + (endtime - startTime) + "ms");
            cdladder.countDown();
        }

    }
    /*LongAdder的执行测试*/
    public void testLongAdder() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        LongAdderTask longAdderTask = new LongAdderTask(startTime);
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(longAdderTask);
        }
        cdladder.await();
        exe.shutdown();
    }
    
	public static void main(String[] args) throws Exception {
		LongAdderDemo demo = new LongAdderDemo();
		demo.testSync();
		demo.testAtomic();
		demo.testLongAdder();
	}
}
