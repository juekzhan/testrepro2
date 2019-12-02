 package com.xiangxueJVM.ch7.p10;

import java.util.Collections;
import java.util.List;

/**
 * 返回零长度的数组或集合，不要返回null
 * 方法的结果返回null，会导致调用方的要单独处理为null的情况。返回零长度，调用方可以统一处理，如使用foreach即可。
*JDK中也为我们提供了Collections.EMPTY_LIST这样的零长度集合
 */
public class Tools {
	  private Tools(){
	    }
	    @SuppressWarnings("rawtypes")
		public static List find13(){
	        ///啪啪啪的代码
	        //return null;
	        return Collections.EMPTY_LIST;
	    }

	    @SuppressWarnings("rawtypes")
		public static void main(String[] args) {
	        List list = find13();
	        if(list ==null){
	            //空处理
	        }

	    }
}
