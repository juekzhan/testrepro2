package com.juekjava.designPattern.adapter1;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIterator implements Iterator<Object> {
    
	Enumeration<Object> enum22; 
	
	public EnumerationIterator(Enumeration<Object> enum22) {
	  this.enum22 = enum22;
	}
	
	
	@Override
	public boolean hasNext() {
		return enum22.hasMoreElements();
	}

	@Override
	public Object next() {
		return enum22.nextElement();
	}

	@Override
	public void remove() {
		Iterator.super.remove();
	}
	
}
