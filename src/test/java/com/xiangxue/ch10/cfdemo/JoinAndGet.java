package com.xiangxue.ch10.cfdemo;

import java.util.concurrent.CompletableFuture;

public class JoinAndGet {
  @SuppressWarnings("unused")
public static void main(String[] args) throws Exception {
	CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
		int i = 1/0;
		return 100;
	});
	future.get();
	//future.join();
}
}
