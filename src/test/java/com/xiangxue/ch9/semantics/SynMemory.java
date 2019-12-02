package com.xiangxue.ch9.semantics;

import com.xiangxue.ch1.SleepTools;
/**
 * 类说明：演示锁的内存语义
 */
public class SynMemory {
	private static volatile boolean ready;
    private static int number;

    private static class PrintThread extends Thread{
        @Override
        public void run() {
            while(!ready){
                ;//System.out.println("number = "+number);      //强制刷到内存
            }
            System.out.println("number = "+number);
        }
    }

    public static void main(String[] args) {
        new PrintThread().start();
        SleepTools.second(5);
        number = 51;
        ready = true;
        SleepTools.secondS(5);
        System.out.println("main is ended!");
    }
}
