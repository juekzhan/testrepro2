package com.juekjava.threadConcurrency;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class UnfafeSequence {
	
	private int value;
	
	public int getNext() {
		return value++;
	}
	
  public static void main(String[] args) {
	
}
}
