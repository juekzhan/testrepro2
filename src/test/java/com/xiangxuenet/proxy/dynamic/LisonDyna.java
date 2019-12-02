package com.xiangxuenet.proxy.dynamic;

import java.lang.reflect.Proxy;

import com.xiangxuenet.proxy.IGetServer;
import com.xiangxuenet.proxy.Receptionist;

public class LisonDyna {
  public static void main(String[] args) {
	  IGetServer james =(IGetServer)
              Proxy.newProxyInstance(IGetServer.class.getClassLoader(),
                      new Class[]{IGetServer.class},
                      new JamesDyna(new Receptionist()));
	  //第一个参数，这个类的classLoader  通过classLoader来确定唯一的实体对象 ，一般接口
	  //第二个参数是数组， 数组中的.class 代理的class对象接口
	  //第三个参数就是真实的代理 对象
      james.choice("御姐范风格");
}
}
