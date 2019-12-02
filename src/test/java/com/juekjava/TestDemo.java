package com.juekjava;

public class TestDemo {
	public static void main(String[] args) {
	 String moblieStr = "18912312678";
	  //String moblieStr = "18000901921";
	  StringBuilder outBuilder = new StringBuilder();	
	  int lengthNums = moblieStr.length();
	  int i = 0;
	  while(i < (lengthNums -1)) {
		  outBuilder.append(moblieStr.substring(i,i+2) + ",");
		  i++;
	   }
	  outBuilder.deleteCharAt(outBuilder.length()-1);
	  if(moblieStr.contains("0")) {
		  StringBuilder neiSb = new StringBuilder();	
		  String[] arrays = outBuilder.toString().split(","); 
		  for(int j=0 ;j<arrays.length;j++) {
			  String str = arrays[j];
			  if(str.contains("0")) {
				  if(str.endsWith("0") && str.startsWith("0")) {
					  neiSb.append("0");
				  }else if(str.endsWith("0")) {
					  if(!arrays[j-1].contains("0")) {
					   neiSb.append(str);  
					  }else {
						  neiSb.append("0");
					  }
				  }else if(str.startsWith("0") && j > 0) {
					   neiSb.append(str.substring(1,2)+",");
					   neiSb.append(str.substring(1,2));     
				  }
			  }else {
				  if(j > 0) {
					  if(!arrays[j-1].contains("0")) {
					         neiSb.append(str+","); 
						  }else {
							  neiSb.deleteCharAt(neiSb.length()-1);
						  }
				  }else {
					  neiSb.append(str+",");    
				  }
			  }
		  }
		  String[] arrays1 = neiSb.toString().split(","); 
		  int index = arrays1.length-1 ;
		  if(arrays1[index].endsWith("0")) {
			  neiSb.delete(neiSb.lastIndexOf(","), neiSb.length());
			  neiSb.append(arrays1[index].substring(1, arrays1[index].length()));
		  }else {
			  neiSb.delete(neiSb.lastIndexOf(","), neiSb.length());
		  }
		  System.out.println(neiSb.toString());
	  }else {
	      System.out.println(outBuilder.toString());
	  }
	}
}
