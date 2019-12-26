package com.juekjava.designPattern.observer;

import java.util.ArrayList;
import java.util.List;

public class SubjectImpl implements Subject {
   
	private List<SubChild> al = new ArrayList<>();
	
	@Override
	public void addWath(SubChild obj) {
		al.add(obj);
	}

	@Override
	public void delWath(SubChild obj) {
		al.remove(obj);
		
	}

	@Override
	public void tongZhi(String stauts) {
		for(SubChild obj : al) {
			obj.update(stauts);
		}
		
	}

}
