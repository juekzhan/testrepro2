package com.juekjava.easyExcel;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

@Data
public class TestDomain {
  @ExcelProperty("字符串标题")
  private String str;
  
  @ExcelProperty("日期标题")
  private Date date;
  
  @ExcelProperty("数字")
  private BigDecimal bigDecimal;
	
}
