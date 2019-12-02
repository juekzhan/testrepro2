package com.xiangxueJVM.ch4.deencrpt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

/*
 * 类说明：加密和解密的服务类
 */
public class XorEncrpt {
	 //位运算：异或操作（一个数经过两次异或=自己）^
	private void  xor(InputStream in,OutputStream out) throws Exception {
		int ch;
		while(-1 != (ch = in.read())) {
			ch = ch ^ 0xff;
			out.write(ch);
		}
	}
	//加密方法（流的 方式），加密后重新写入
	public void encrypt(File src,File des) throws Exception{
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(des);
		
		xor(in,out);
		in.close();
		out.close();
	}
	//解密方法，返回解密后的二进制数组
	public byte[]  decrypet(File src) throws Exception{
		InputStream  in = new FileInputStream(src);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		xor(in,bos);
		byte[]  data = bos.toByteArray();
		return data;
	}
	
	public static void main(String[] args) throws Exception {
		File src = new File("D:\\esp\\project-test\\target\\test-classes\\com\\xiangxueJVM\\ch4\\deencrpt\\DemoUserSrc.class");
		File dest = new File("D:\\esp\\project-test\\target\\test-classes\\com\\xiangxueJVM\\ch4\\deencrpt\\DemoUser.class");
		 XorEncrpt demoEncryptUtil = new XorEncrpt();
		 demoEncryptUtil.encrypt(src,dest);
		 System.out.println("加密完成！");
	}
}
