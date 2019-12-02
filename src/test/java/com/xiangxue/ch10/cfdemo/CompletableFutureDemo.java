package com.xiangxue.ch10.cfdemo;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

//CompletableFuture 的用法
public class CompletableFutureDemo {
 //所谓异步调用其实就是实现一个可无需等待被调用函数的返回值而让操作继续运行的方法
 // 在 java语言中，简单的讲就是另启一个线程来完成调用中的部分计算，
 //使调用继续运行或返回，而不需要等待计算结果。但调用者仍需要取线程的计算结果。
	
	public static void main(String[] args) throws Exception {
		//supplyAsync() ;
//		testFuture();
		//runAsync();
		//supplyAsync() ;
		//whenComplete();
		//thenApply();
		//handle();
		//thenAccept();
		//thenRun();
		//thenCombine();
		thenAcceptBoth();
	}
	
 @SuppressWarnings("unused")
public static void testFuture() throws Exception {
	 ExecutorService  service = Executors.newSingleThreadExecutor();
	 Future<Integer> future = service.submit(()->{
		 try {
			 Thread.sleep(5000);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return 1;
	 });
	 System.out.println(future.get());  //阻塞到这个地方  也可以让异步转化成同步
	 System.out.println("finish");
	 service.shutdown();
 }
 //CompletableFuture 提供了四个静态方法来创建一个异步操作。
// public static CompletableFuture<Void> runAsync(Runnable runnable)
// public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
// public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
// public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor

//没有指定Executor的方法会使用ForkJoinPool.commonPool() 作为它的线程池执行异步代码。
 //如果指定线程池，则使用指定的线程池运行。以下所有的方法都类同。
 //runAsync方法不支持返回值。
 //supplyAsync可以支持返回值。
 //无返回值
 public static void runAsync() throws Exception{
	 CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
		try {
			System.out.println("运行的无返回值。。。");
			TimeUnit.SECONDS.sleep(5);
		}catch (Exception e) {
          e.printStackTrace();
		}
		System.out.println("run end ...");
	 });
	System.out.println(future.get());
	System.out.println("完成之后.....");
 }
 //有返回值
 public static void supplyAsync() throws Exception{
	 CompletableFuture<Long>  future = CompletableFuture.supplyAsync(()->{
		 System.out.println("run START ...");
		 try {
			 TimeUnit.SECONDS.sleep(5);  	 
		 }catch (Exception e) {
		 }
		 System.out.println("run end ...");
		return System.currentTimeMillis(); 
	 });
	 long time = future.get();
	 System.out.println("time = "+time);
 }
 //计算结果完成时的回调方法
  //当CompletableFuture的计算结果完成，或者抛出异常的时候，可以执行特定的Action。主要是下面的方法：
 //public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action)
 //public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
 //public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
 //public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn)
 //异步回调方法 ,完成回调
 @SuppressWarnings("unused")
public static void whenComplete() throws Exception{
	 CompletableFuture<Void>  future = CompletableFuture.runAsync(()->{
		try {
			TimeUnit.SECONDS.sleep(1);
		}catch (Exception e) {
			
		}
		if(new Random().nextInt()%2>=0) {
			int i  = 12/0;
		}
		System.out.println("run  end .....");
	 });
	 future.whenComplete(new BiConsumer<Void, Throwable>() {

		@Override
		public void accept(Void t, Throwable u) {
			System.out.println("执行完成~！");
		}
	});
	 future.exceptionally(new Function<Throwable, Void>() {

		@Override
		public Void apply(Throwable t) {
			System.out.println("执行失败"+t.getMessage());
			return null;
		}
	});
	  TimeUnit.SECONDS.sleep(2);
 }
 //thenApply
 //当线程依赖另一个线程 。可以 使用thenApply方法来把这连个个线程串行化
  //public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
 //public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
 //public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
// Function<? super T,? extends U>
 //T：上一个任务返回结果的类型
 //U：当前任务的返回值类型supplyAsync
 public static void thenApply() throws Exception{
	 CompletableFuture<Long> future = CompletableFuture.supplyAsync(()->{
		 long result = new Random().nextInt(100);
         System.out.println("result1="+result);
         return result;
	 });
	 future.thenApply(new Function<Long, Long>() {

		@Override
		public Long apply(Long t) {
			long result = t*5;    //第二个任务依赖第一个任务的结果。
            System.out.println("result2="+result);
            return result;
		}
	});
	 long result = future.get();
	 System.out.println(result);
 }
 
 // handle 方法
 //handle 是执行任务完成时对结果的处理。
 // handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，
 //还可以处理异常的任务。thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
 //public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
 //public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
 //public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor);
 public static void handle() throws Exception{
	 CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
		//int i = 10/0;
		return new Random().nextInt(10);
	 });
	 
	CompletableFuture<Integer> mm = future.handle(new BiFunction<Integer, Throwable, Integer>() {

		@Override
		public Integer apply(Integer t, Throwable throwable) {
			 int result = -1;
	            if(throwable==null){
	                result = t * 2;
	            }else{
	                System.out.println(throwable.getMessage() +  "异常消息是 ~!");
	            }
	            return result;
		}
	});
	 System.out.println(future.get());
	 System.out.println(mm.get());
 }
 
 // thenAccept 消费处理结果
// 接收任务的处理结果，并消费处理，无返回结果。
 //public CompletionStage<Void> thenAccept(Consumer<? super T> action);
 //public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
 //public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
 //从示例代码中可以看出，该方法只是消费执行完成的任务，并可以根据上面的任务返回的结果进行处理。并没有后续的输错操作。
 public static void thenAccept() throws Exception{
	 CompletableFuture<Void> future =  CompletableFuture.supplyAsync(()->{
		 return new Random().nextInt(10);
	 }).thenAccept((integer)->{
		 System.out.println(integer);
	 });
	 System.out.println(future.get());
 }
 
 //thenRun 方法
 //跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenAccept 。
// public CompletionStage<Void> thenRun(Runnable action);
 //public CompletionStage<Void> thenRunAsync(Runnable action);
 //public CompletionStage<Void> thenRunAsync(Runnable action,Executor executor);
 //该方法同 thenAccept 方法类似。不同的是上个任务处理完成后，并不会把计算的结果传给 thenRun 方法。只是处理玩任务后，执行 thenAccept 的后续操作。
 public static void thenRun()  throws Exception{
	 CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->{
		 return new Random().nextInt(10);
	 }).thenRun(()->{
		 System.out.println("then Run....");
	 });
	 System.out.println(future.get());
 }
 //thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
 //public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
 //public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
 //public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn,Executor executor);
 public static void thenCombine() throws Exception{
	 CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
	       return "hello"; 
	 });
	    CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
	    	return "hello"; 
	    });
	   
	   CompletableFuture<String> result = future1.thenCombine(future2,new BiFunction<String, String, String>() {

		@Override
		public String apply(String t, String u) {
			return t + "    " + u;
		}
	});
	   System.out.println(result.get());
 }
 //thenAcceptBoth
 //当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
// public <U> CompletionStage<Void> thenAcceptBoth(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action);
 //public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action);
 //public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action,     Executor executor);
   public static void thenAcceptBoth() throws Exception{
	   CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(()->{
		  int t = new Random().nextInt(3);
		  try {
			TimeUnit.SECONDS.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		  System.out.println("f1="+ t);
		  return t;
	   });
	   CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(()->{
		   int t = new Random().nextInt(3);
			  try {
				TimeUnit.SECONDS.sleep(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			  System.out.println("f2="+ t);
			  return t;
	   });
	   CompletableFuture<Void> result = f1.thenAcceptBoth(f2, (t,u)->{
		   System.out.println("f1="+t +"; " +  "f2="+u);
	   });
//	   System.out.println(f1.get());
//	   System.out.println(f2.get());
	   System.out.println(result.get());     //不加get 是不行的，必须加上get 才 有可能执行，因为 是异步
   }
}
