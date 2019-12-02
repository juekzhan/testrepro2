package com.xiangxue.cha8a.vo;
 /**
  * 
  * @ClassName: TaskResultType  
  * @Package :com.xiangxue.cha8a.vo
  * @Description: 方法本身运行是否结果正确的的类型
  * @Author: shuling.zhan
  * @Email: shuling.zhan@baozun.com
  * @Date: 2019年7月23日 下午2:30:32
  */
public enum TaskResultType {
 Sucess,  //方法执行完成，业务结果也正确
 Failure,  //方法执行完成，业务 结果处理错误 
 Exception   //方法执行抛出了异常
}
