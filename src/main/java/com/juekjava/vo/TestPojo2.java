package com.juekjava.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Component
@PropertySource(value= {"classpath:testPojo.properties"})
@ConfigurationProperties(prefix = "testPojo2")
public class TestPojo2 {
	private String id;
	 
	private String value;  
}
