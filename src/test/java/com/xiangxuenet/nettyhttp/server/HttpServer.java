package com.xiangxuenet.nettyhttp.server;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
/**
 * 
 * @ClassName: HttpServer  
 * @Package :com.xiangxuenet.nettyhttp.server
 * @Description: 可以直接在浏览器上进行访问的
 * @Author: shuling.zhan
 */
public class HttpServer {
  public static final int port = 6789; //设置服务器端口号
  
  private static EventLoopGroup group = new NioEventLoopGroup();
  
  private static final boolean SSL = true; // 加https 请求
  
  private static final ServerBootstrap b = new ServerBootstrap();
  
  /**
   *  
   * @Title: main   
   * @Description: https 服务器端
   * @param args      
   * @return: void      
   * @throws InterruptedException 
   * @throws CertificateException 
   * @throws SSLException 
   * @throws  
   * @author: shuling.zhan
   */
  public static void main(String[] args) throws InterruptedException, CertificateException, SSLException {
	  final SslContext sslContext;
	  if(SSL) {
		  //https进行
		  SelfSignedCertificate ssc = new SelfSignedCertificate(); 
		  sslContext = SslContextBuilder.forServer(ssc.certificate(),
                  ssc.privateKey()).build();
		  //sslContext = null;
	  }else {
		  sslContext = null;
	  }
	  
	try {
		b.group(group).channel(NioServerSocketChannel.class)
		.childHandler(new ServerHandlerInit(sslContext));
		
		ChannelFuture f = b.bind(port).sync();
		System.out.println("服务器端启动成功，端口是：" + port);
		f.channel().closeFuture().sync();
	}finally {
		group.shutdownGracefully().sync();
	}
 }
}
