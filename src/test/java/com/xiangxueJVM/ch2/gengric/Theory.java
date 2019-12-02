package com.xiangxueJVM.ch2.gengric;

import java.util.HashMap;
import java.util.Map;

//泛型擦除 查看编译的class 文件中  还是 (String)map.get("juek")
public class Theory {
   public static void main(String[] args) {
	Map<String, String> map = new HashMap<>();
	map.put("juek","18");
	System.out.println(map.get("juek"));
}
}
