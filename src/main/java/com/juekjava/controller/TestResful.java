package com.juekjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juekjava.vo.TestPojo;
import com.juekjava.vo.TestPojo2;

@RestController
@RequestMapping("/test")
public class TestResful {
    
	@Autowired
	private TestPojo testPojo;
	
	@Autowired
	private TestPojo2 testPojo2;
	
	@PostMapping("/testDemoMethod")
	public TestPojo2 testDemoMetghod(@RequestBody TestPojo testPojo) {
		TestPojo rtnTestPojo = new TestPojo();
		rtnTestPojo.setId(testPojo.getId());
		rtnTestPojo.setValue(testPojo.getValue());
		return this.testPojo2;
	}
	
	
}
