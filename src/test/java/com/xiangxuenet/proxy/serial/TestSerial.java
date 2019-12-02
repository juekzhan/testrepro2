package com.xiangxuenet.proxy.serial;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestSerial {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
      //创建一个User对象
		UserInfo user = new UserInfo("Juek","12345");
		//创建一个List 对象
		List<String> list = new ArrayList<String>();
		list.add("my name");
		list.add(" is");
		list.add("Juek");
		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(new FileOutputStream("D:/Serial.txt"));
			os.writeObject(user);
			os.writeObject(list);
		}catch (Exception e) {
         e.printStackTrace();
		}finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		try {
          ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                  "D:/Serial.txt"));
          UserInfo temp = (UserInfo) is.readObject();// 从流中读取User的数据
          System.out.println(temp.getPhone());
          System.out.println(temp.getName());
          List tempList = (List) is.readObject();// 从流中读取List的数据
          for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
              System.out.print(iterator.next());
          }
          is.close();
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
		
	}

}
