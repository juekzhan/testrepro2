package com.juekjava;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


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


import net.minidev.json.JSONObject;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureMockMvc
public class TestResfulTest {
  
  @Autowired
  private MockMvc mockMvc;
  
//  @Autowired
//  private WebApplicationContext wac;
  
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
//	  BigDecimal yy = new BigDecimal(10).divide(new BigDecimal(1), 12 , BigDecimal.ROUND_DOWN);
//	  System.out.println(yy);
//	  StringBuilder sb = new StringBuilder();
//	  sb.append("1,2,3,");
//	  sb.deleteCharAt(sb.length()-1);
//	  System.out.println(sb.toString());
//	  Calendar c = Calendar.getInstance();
//  	c.setTime(new  Date());
//  	c.add(Calendar.DATE, -1);
//  	System.out.println(c.getTime());
//	  List<String> listStr = new ArrayList<>();
//	  listStr.add("1");
//	  System.out.println(String.join(",", listStr));
//	  BigDecimal big = new BigDecimal("2.1201");
//	  System.out.println(big.stripTrailingZeros().toPlainString());
//	  
//	  String ss = "123327890";
//	  System.out.println(ss.substring(ss.length() - 8,ss.length()));
	 // System.out.println(-4 >> 5);
//	  TimeUnit unit = TimeUnit.SECONDS;
//	  long times = 3*1000 + System.currentTimeMillis();
//	  System.out.println(unit.convert(times-System.currentTimeMillis(), unit));
	 // String  jonsnStr = "{}";
	  List<Hosting> hostings = new ArrayList<>();
	  hostings.add(new Hosting(1, "liquidweb.com", 80000,new BigDecimal(2.0f)));
	  hostings.add(new Hosting(10, "liquidweb.com", 50000,new BigDecimal(2.0f)));
	  hostings.add(new Hosting(10, "liquidweb.com", 40000,new BigDecimal(2.0f)));
	  hostings.add(new Hosting(10, "liquidweb.com", 180000,new BigDecimal(2.0f)));
	  hostings.add(new Hosting(20, "linode.com", 90000,new BigDecimal(2.0f)));
//	  hostings.add(new Hosting(22, "linode.com", 90000));
//	  hostings.add(new Hosting(4, "aws.amazon.com", 200000));
//	  hostings.add(new Hosting(5, "mkyong.com", 1));
//	  Map<String,List<Hosting>> maps = hostings.stream().collect(Collectors.groupingBy(Hosting::getName));
//	  //System.out.println(maps.size());
//	  maps.keySet().forEach(key->{
//		  System.out.println(maps.get(key).get(0).getName());
//	  });
	  System.out.println(hostings.stream().mapToInt(p->p.getId()).sum());
//	  maps.get("liquidweb.com").forEach(temp->{
//		  System.out.print("name:"+temp.getName() +"\t");
//		  System.out.print("id:"+temp.getId()+"\t");
//		  System.out.println("websites:"+temp.getWebsites());
//	  });
//	  AtomicReference<BigDecimal> actB = new AtomicReference<>();
//	  actB.set(new BigDecimal(1.0f).add(new BigDecimal(2.0f)));
	 // actB.set(new BigDecimal(1.0f));
	 // System.out.println(actB.get());
//	  AtomicInteger act = new AtomicInteger();
//	  act.getAndAdd(1);
//	  act.getAndAdd(1);
//	  act.getAndAdd(1);
//	  act.getAndAdd(1);
//	  act.getAndAdd(1);
//	  System.out.println(act.get());
  }
  

@SuppressWarnings("unused")
private static boolean isBase64(String str) {
    String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
    return Pattern.matches(base64Pattern, str);
}

  
  @Test
  public void testMain() {
	
  }
  
  

private boolean isjson(String string){
//    try {
//        JSONObject jsonStr= JSON.toJSONString(string);
//        return  true;
//    } catch (Exception e) {
//        return false;
//    }
	return false;
}


  public void test_nullsub() {
	  BigDecimal biga = new BigDecimal(2.0f);
	 // BigDecimal a = new BigDecimal(null);
  }

//  private static void addAa(int aa) {
//	  aa = 8;
//  }
}




class Hosting {
	   private int id;

	    private String name;

	    private long websites;

	    private BigDecimal amout;
	    
	    
		public Hosting(int id, String name, long websites,BigDecimal amount) {
			this.id = id;
			this.name = name;
			this.websites = websites;
			this.amout = amount;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public long getWebsites() {
			return websites;
		}

		public void setWebsites(long websites) {
			this.websites = websites;
		}

		public BigDecimal getAmout() {
			return amout;
		}

		public void setAmout(BigDecimal amout) {
			this.amout = amout;
		}
	    
		
	    
}
