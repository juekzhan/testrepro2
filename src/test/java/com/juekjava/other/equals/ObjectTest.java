package com.juekjava.other.equals;

import java.util.ArrayList;
import java.util.List;

public class ObjectTest {
	private String id;

	private String name;
	
	public ObjectTest(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	@Override
	public boolean equals(Object obj) {
		ObjectTest oldObj = (ObjectTest) obj;
		String oldId = oldObj.getId(); 
		if(this.id.equals(oldId)) {
			return true;
		}
		return false;
	}
    
	@Override
	public String toString() {
		return "id"+ this.getId() + "  name" + this.getName();
	}

	public static void main(String[] args) {
		ObjectTest objectTest1 = new ObjectTest("1","張三"); 
		ObjectTest objectTest2 = new ObjectTest("1","李思"); 
		ObjectTest objectTest3 = new ObjectTest("2","www"); 
		ObjectTest objectTest4 = new ObjectTest("2","yyy"); 
		ObjectTest objectTest5 = new ObjectTest("3","111"); 
		ObjectTest objectTest6 = new ObjectTest("4","aaaa"); 
		ObjectTest objectTest7 = new ObjectTest("5","bbbb"); 
		List<ObjectTest> list = new ArrayList<>();
		list.add(objectTest1);
		list.add(objectTest2);
		list.add(objectTest3);
		list.add(objectTest4);
		list.add(objectTest5);
		list.add(objectTest6);
		list.add(objectTest7);
		
		System.out.println(objectTest1.equals(objectTest2));
		
		for(ObjectTest objectTest : list) {
			System.out.println(objectTest);
		}
		
		
		
	}
}
