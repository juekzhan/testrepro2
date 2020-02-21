package com.juekjava.web.shortpolling;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



// 短轮询
public class ShowTimeController {
	private static Logger logger = LoggerFactory.getLogger(ShowTimeController.class);
	
	
	 @RequestMapping("/time")
	    public String normal(){
	        return "showtime";
	    }
	 
	 @RequestMapping(value="/showTime",produces = "text/html;charset=UTF-8")
	    @ResponseBody
	    public String getTime(){
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        return formatter.format(new Date());
	    }
}
