package com.xiangxueJVM.ch4.deencrpt;

import java.io.File;

//自定义类加载器 自定义的类加载器,进行异或解密。URL
public class CustomClassLoader extends ClassLoader {
	@SuppressWarnings("unused")
	private final String name;
	private String basePath;
	private final static String FILE_EXT = ".class";

	public CustomClassLoader(String name) {
		this.name = name;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
   
	private byte[] loadClassData(String name) {
		byte[] data = null;
		XorEncrpt demoEncryptUtil = new XorEncrpt();
		try {
			String tempName = name.replace(".","\\");
			data = demoEncryptUtil.decrypet(new File(basePath+tempName+FILE_EXT));
		}catch (Exception e) {
          e.printStackTrace();
		}
		return data;
	}
     //返回对应的类
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = this.loadClassData(name);
		return this.defineClass(name,data,0,data.length);
	}
	
//  @Override
//  public Class<?> loadClass(String name) throws ClassNotFoundException {
//      if(name.indexOf("java.")<5&&name.indexOf("java.")>-1){
//          return super.loadClass(name);
//      }
//      byte[] data = this.loadClassData(name);
//      if (data == null){
//          return super.loadClass(name);
//      }
//      return this.defineClass(name,data,0,data.length);
//  }
	
}
