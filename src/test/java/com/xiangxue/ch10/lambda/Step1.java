package com.xiangxue.ch10.lambda;

import java.util.ArrayList;
import java.util.List;

public class Step1 {
 //挑选出半径为2的园
 public List<Circle> getCircle(List<Circle> circles){
	 List<Circle> cList = new ArrayList<Circle>();
	 for(Circle  circle  : circles) {
		 if(circle.getRadius() == 2) {
			 cList.add(circle);
		 }
	 }
	 return cList;
 }
 
 /*2.1、选择条件参数化，根据颜色挑选出圆*/
 public static List<Circle> getCircleByColor(List<Circle> circles, String color){
     List<Circle> circleList = new ArrayList<>();
     for(Circle circle :circles){
         if(color.equals(circle.getColor())){
             circleList.add(circle);
         }
     }
     return circleList;
 }
 
 /*2.2、选择条件参数化，根据半径挑选出圆*/
 public static List<Circle> getCircleByRadius(List<Circle> circles, int radius){
     List<Circle> circleList = new ArrayList<>();
     for(Circle circle :circles){
         if(radius==circle.getRadius()){
             circleList.add(circle);
         }
     }
     return circleList;
 }
 
 /*2.3、疑问？很明显，即使选择条件参数化，还是有问题的：
  * a、选择条件变化了，那么相应的方法也要变
  * b、如果我要根据多个条件选择，怎么办？难道把所有的条件都传入吗？
  * 于是，我们考虑定义一个挑选圆的接口，看Step02*/
}
