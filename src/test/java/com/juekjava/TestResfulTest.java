package com.juekjava;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import ch.qos.logback.core.net.SyslogOutputStream;
import net.minidev.json.JSONObject;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureMockMvc
public class TestResfulTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private WebApplicationContext wac;
  
  @Test
  public void test1() throws Exception {
	  Map<String,Object> map = new HashMap<String,Object>(2);
	  map.put("id", "9");
	  map.put("value", "zhanjuek");
	  
	  MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/test/testDemoMethod")
			  .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
			  .andExpect(MockMvcResultMatchers.status().isOk())
			  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
			  .andReturn();
	  System.out.println(result.getResponse().getContentAsString());
  }
  
  
  public static void main(String[] args) {
	  BigDecimal bigDecimal = new BigDecimal("1.1200");
	  System.out.println(bigDecimal.scale());
	 
	 
  }
  
  @Test
  public void testMain() {
	  
  }
  
}
