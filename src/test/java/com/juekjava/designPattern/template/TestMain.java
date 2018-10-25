package com.juekjava.designPattern.template;

public class TestMain {

	public static void main(String[] args) {
       System.out.println("客人想要Tea");
       
       CaffeeineBeverage tea = new Tea();
       tea.prepareRecipe();
       
       System.out.println("客人想要Coffee");
       
       CaffeeineBeverage cofee = new Coffee();
       cofee.prepareRecipe();
	}
    
}
