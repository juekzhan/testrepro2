package com.xiangxueJVM.ch4.deencrpt;

public class DemoRun {
 public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	CustomClassLoader demoCustomClassLoader = new CustomClassLoader("My ClassLoader");
	//设置类的加载路径
	demoCustomClassLoader.setBasePath("D:\\esp\\project-test\\target\\test-classes\\");
	 Class<?> clazz = demoCustomClassLoader.findClass("com.xiangxueJVM.ch4.deencrpt.DemoUser");
	 System.out.println(clazz.getClassLoader());
     Object o = clazz.newInstance();
     System.out.println(o);
}
}
