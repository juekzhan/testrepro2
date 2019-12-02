package com.xiangxueJVM.ch7.p14;
/**
 * 当心字符串连接的性能
 */
public class TestString {
	 //一个日志类
    private static class Log{

        public static void debug(String msg){
            if (isDebug()) System.out.println(msg);
        }
        public static boolean isDebug(){
            return false;
        }
    }
    
    public static void main(String[] args) {
    	 int count = 1000000; //一百万次
         long start = System.currentTimeMillis();
         //直接打印模式
         for(int i = 0;i<count;i++){//虽然不会打印，但是字符串拼接已经做了
             Log.debug("The system is running and the time is "
                     +System.currentTimeMillis()
                     +" now,Let's do another thing:"+System.nanoTime());

         }
         System.out.println("直接打印模式,次数："+count+"：spend time :"
                 +(System.currentTimeMillis()-start)+"ms");



         start = System.currentTimeMillis();
         //先判断再打印模式
         for(int i = 0;i<count;i++){//虽然不会打印，不会进行字符串拼接
             if(Log.isDebug())
                 Log.debug("The system is running and the time is "
                         +System.currentTimeMillis()
                     +" now,Let's do another thing:"+System.nanoTime());
         }
         System.out.println("先判断再打印模式,次数："+count+"：spend time :"
                 +(System.currentTimeMillis()-start)+"ms");


     
	}
}
