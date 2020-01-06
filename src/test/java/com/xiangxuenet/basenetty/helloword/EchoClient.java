package com.xiangxuenet.basenetty.helloword;
/**
 * 
 * @ClassName: EchoClient  
 * @Package :com.xiangxuenet.basenetty
 * @Description: netty 客户端 
 * @Author: shuling.zhan
 */

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {
	private final int port;
	
	private final String host;
	
    public EchoClient(int port, String host) {
		this.port = port;
		this.host = host;
	}
    
    public void start() throws InterruptedException{
    	//线程组  采用nio方式
    	EventLoopGroup group = new NioEventLoopGroup();
     try {
    	Bootstrap b = new Bootstrap();
    	b.group(group)./**把线程组 传入**/
    	channel(NioSocketChannel.class)./*指定使用NIO进行网络传输*/
    	remoteAddress(new InetSocketAddress(host,port)).  //地址
    	handler(new EchoClientHandle());  //处理器 ，接收到 出路读写
    	/*连接到远程节点，阻塞直到连接完成*/
    	ChannelFuture f = b.connect() //发起链接   //这边阻塞 ，要设置上阻塞 链接
    			.sync();
    	//阻塞程序直到关闭
    	f.channel().closeFuture().sync(); //拿到channel  等到链接关闭事件发生 
     }finally {
    	 group.shutdownGracefully().sync(); 
     }
    }
	
	public static void main(String[] args) throws InterruptedException {
		new EchoClient(9999, "127.0.0.1").start();
	}
}
