package com.xiangxueJVM.ch7.p13;

/**
 * 将局部变量的作用域最小化
 */
public class Demo {
	 @SuppressWarnings("unused")
	public static void main(String[] args) {
         int x=2,y=2; //.......代码开头 把变量全部定义
        //啪啪啪
        x=2;//不小心的写

        //啪啪啪
        //怕怕
        try{

            if(x ==1){

            }
        }catch (Exception e){

        }


    }
}
