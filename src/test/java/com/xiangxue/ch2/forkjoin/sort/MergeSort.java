package com.xiangxue.ch2.forkjoin.sort;

import java.util.Arrays;

import com.xiangxue.ch2.forkjoin.MakeArray;

/**
 * 
 * @ClassName: MergeSort  
 * @Package :com.xiangxue.ch2.forkjoin.sort
 * @Description: 归并排序
 * @Author: shuling.zhan
 * @Email: shuling.zhan@baozun.com
 * @Date: 2019年5月5日 下午4:09:39
 */
public class MergeSort {
  public  static int[] sort(int[] array) {
	  if(array.length <= MakeArray.THRESHOLD) {
		  return InsertionSort.sort(array);
	  }else {
		  /*切分数组，然后递归调用*/
          int mid = array.length / 2;
          int[] left = Arrays.copyOfRange(array, 0, mid);
          int[] right = Arrays.copyOfRange(array, mid, array.length);
          return merge(sort(left), sort(right));
	  }
  }
  
  /**
   * 
   * @Title: merge   
   * @Description: 合并数组算法
   * @param left
   * @param right
   * @return      
   * @return: int[]      
   * @throws  
   * @author: shuling.zhan
   * @date :2019年5月5日 下午5:01:59
   */
  public static int[] merge(int[] left, int[] right) {
	  int[] result = new int[left.length + right.length];
	  for(int index = 0  , i = 0, j = 0; index <result.length ; index ++) {
		  if (i >= left.length)/*左边数组已经取完，完全取右边数组的值即可*/
              result[index] = right[j++];
          else if (j >= right.length)/*右边数组已经取完，完全取左边数组的值即可*/
              result[index] = left[i++];
          else if (left[i] > right[j])/*左边数组的元素值大于右边数组，取右边数组的值*/
              result[index] = right[j++];
          else/*右边数组的元素值大于左边数组，取左边数组的值*/
              result[index] = left[i++];
	  }
	  return result;
  }
  
  public static void main(String[] args) {
	  System.out.println("============================================");
      long start = System.currentTimeMillis();
      MergeSort.sort(MakeArray.makeArray());
      System.out.println(" spend time:"+(System.currentTimeMillis()-start)+"ms");
}
}
