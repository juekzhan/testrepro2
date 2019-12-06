package com.juekjava;

public class TestException {
  
	public static void main(String[] args) {
      try {
		aa();
      }catch (Exception e) {
    	  System.out.println(e.getMessage());
    	  System.out.println("捕捉异常");
      }
	}
	
	
	private static void aa() {
		int i = 2;
		if(i == 1) {
		 throw new RuntimeException("不合理");
		}
		System.out.println("正常的业务流程");
	}

}
