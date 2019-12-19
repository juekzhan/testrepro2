package com.poitest;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;

public class TestExcel {
  
	@Test
	public void writeExcelOneSheetOnceWrite() throws Exception{
		 OutputStream out = new FileOutputStream("D:\\temp\\withoutHead1.xlsx");
		 ExcelWriter writer = new ExcelWriter(out,ExcelTypeEnum.XLSX);
		 
		 //设置sheet
		 Sheet sheet1 = new Sheet(0);
		 sheet1.setSheetName("sheet1");
		 
		 
		 Sheet sheet2 = new Sheet(2);
		 
		 sheet2.setSheetName("lala");
		 
         Sheet sheet3 = new Sheet(3);
		 
		 sheet3.setSheetName("kkkkk");
		 
		 Table table2 = new Table(2);
		 List<List<String>> titles2 = new ArrayList<List<String>>();
	        titles2.add(Arrays.asList("IDyyy"));
	        titles2.add(Arrays.asList("名称yyy"));
	        titles2.add(Arrays.asList("年龄yyyy"));
	        titles2.add(Arrays.asList("生日yyyy"));
	        table2.setHead(titles2);
		 
	        
	        Table table3 = new Table(3);
			 List<List<String>> titles3 = new ArrayList<List<String>>();
		        titles3.add(Arrays.asList("信息y"));
		        titles3.add(Arrays.asList("操作"));
		        titles3.add(Arrays.asList("年龄从V型从V型yy"));
		        titles3.add(Arrays.asList("生日y徐陈小春yyy"));
		        table3.setHead(titles3);
		 
	    Table table1 = new Table(1);
        List<List<String>> titles = new ArrayList<List<String>>();
        titles.add(Arrays.asList("用户ID"));
        titles.add(Arrays.asList("名称"));
        titles.add(Arrays.asList("年龄"));
        titles.add(Arrays.asList("生日"));
        table1.setHead(titles);
        
        
        
        

     // 查询数据导出即可 比如说一次性总共查询出100条数据
        List<List<String>> userList = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            userList.add(Arrays.asList("ID_" + i, "小明" + i, String.valueOf(i), new Date().toString()));
        }
        writer.write0(userList, sheet1, table1);
        writer.write0(userList, sheet3, table3);
        writer.write0(userList, sheet2, table2);
        
        writer.finish();
	}
	
	
	@Test
	public void  writeExcelAndOrD() throws Exception{
		 List list = null;
		 if(list == null || list.isEmpty()) {
			 System.out.println("aaaa");
		 }
	}
}
