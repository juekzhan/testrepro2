package com.juekjava.designPattern.template;

import java.util.Arrays;

public class DuckSortTestDrive {

	public static void main(String[] args) {
        Duck[] ducks = {
        		new Duck("xiaoO",3),
        		new Duck("xiaoM",4),
        		new Duck("xiaoQ",1),
        		new Duck("xiaoK",8),
        		new Duck("xiaoV",5),
        		new Duck("xiaoY",11),
        		new Duck("xiaoN",1),
        };
        
        System.out.println("Before sorting:");
        for(Duck duck: ducks) {
        	System.out.println(duck);
        }
        
        Arrays.sort(ducks);
        
        System.out.println("After sorting:");
        
        for(Duck duck: ducks) {
        	System.out.println(duck);
        }
	}

}
