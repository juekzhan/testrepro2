package com.xiangxueJVM.ch3;
//可达性分析，就是指 能关联上 GCRoots （不会回收）
// GCRoots 指得是  
//在Java, 可作为GC Roots的对象包括: 
//方法区中类静态属性引用的对象。
//方法区中常量引用的对象。
//虚拟机栈(本地变量表)中引用的对象。
//本地方法栈JNI(Native方法)中引用的对象
public class GCRoots {
   Object o = new Object();
   static Object gcRoot1 = new Object();
   final static Object gcRoot2 = new Object();
   
   @SuppressWarnings("unused")
public static void main(String[] args) {
	//可达
	   Object object1 = gcRoot1;//=不是赋值，在对象中是引用，传递的是右边对象的地址
	   Object object2 = object1;
       Object object3 = object1;
       Object object4 = object3;
  }
   @SuppressWarnings("unused")
public void king() {
	   //不可达（方法运行完后可回收）
	   Object object5 = o;
	   Object object6 = object5;
	   Object object7 = object5;
   }
   
   @SuppressWarnings("unused")
public void  stack() {
	   Object ostack  = new Object(); //本地变量表的对象
	   Object object9 = ostack;
	 //以上object9 在方法没有(运行完)出栈前都是可达的   //都是不可回收
   }
}
