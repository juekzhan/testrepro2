package com.xiangxueJVM.ch4;
//静态分析
public class StaticDispath {
    static abstract class  Human{}
    static class Man extends Human{}
    static class Women extends Human{}
    
    public void sayHello(Human guy) { System.out.println("hello guy!");}
    public void sayHello(Man guy) { System.out.println("hello,gentleman!");}
    public void sayHello(Women guy) { System.out.println("hello lady!");}
    
    
    public static void main(String[] args) {
		Human h1 = new Man();
		Human h2 = new Women();
		
		StaticDispath  sr = new StaticDispath();
		sr.sayHello(h1);
		sr.sayHello(h2);
		//
		Human hu = new Man();
		sr.sayHello((Man) hu);
	}
}
