package com.juekjava.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Component
@ConfigurationProperties(prefix = "testPojo")
public class TestPojo {
 private String id;
 
 private String value;
  
 public static void main(String[] args) {
	
}
}
