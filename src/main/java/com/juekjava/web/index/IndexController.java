package com.juekjava.web.index;

import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController {
	 @RequestMapping("/index")
	  public String hello() {
		  return "index";
	  }
}
