package com.juekjava.designPattern.adapter;



public class TestMain {
  public static void main(String[] args) {
	 MallardDuck duck = new MallardDuck();
	 WildTurkey turkey = new WildTurkey();
      
	 Duck turkeyAdapter = new TurkeyAdapter(turkey);
	 
	 System.out.println("The Turkey says ...");
	 turkey.gobble();
	 turkey.fly();
	 
	 System.out.println("\nThe Duck says ...");
	 duck.quack();
	 duck.fly();
	 
	 System.out.println("\nThe TurkeyAdaoter says ...");
	 turkeyAdapter.quack();
	 turkeyAdapter.fly();
	 
	// Collection<E>
	 
	// Hashtable<K, V>
	 
	 //Iterator<E>
  }
}
