package com.juekjava.easyExcel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.alibaba.excel.EasyExcel;

public class TestMain {
 
	@Test
	public void test1() throws Exception{
		String fileName = "D:\\tmp\\ssss"+  System.currentTimeMillis() + ".xlsx";
		EasyExcel.write(fileName,TestDomain.class).sheet("模板").doWrite(data());;
		
	}
	
	
	
	private List<TestDomain> data() {
	    List<TestDomain> list = new ArrayList<TestDomain>();
	    for (int i = 0; i < 10; i++) {
	    	TestDomain data = new TestDomain();
	        data.setStr("字符串"+ i + "xxx");
	        data.setDate(new Date());
	        data.setBigDecimal(new BigDecimal(0.1f));
	        list.add(data);
	    }
	    return list;
	}
}
