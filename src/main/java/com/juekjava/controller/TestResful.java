package com.juekjava.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juekjava.vo.TestPojo;

@RestController
@RequestMapping("/test")
public class TestResful {
    
	@PostMapping("/testDemoMethod")
	public TestPojo testDemoMetghod(@RequestBody TestPojo testPojo) {
		TestPojo rtnTestPojo = new TestPojo();
		rtnTestPojo.setId(testPojo.getId());
		rtnTestPojo.setValue(testPojo.getValue());
		return rtnTestPojo;
	}
	
	
}
