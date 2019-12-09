package com.xiangxuenet.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServerHandle implements Runnable {
   private Selector selector;   //选择器
   private ServerSocketChannel serverChannel;  //通道
   private volatile boolean started;
	
	public NioServerHandle(int port) {
	  try {
		  selector = Selector.open();
		  serverChannel = ServerSocketChannel.open();
		  serverChannel.configureBlocking(false);//开启非阻塞模式
		  
		  //标记服务器已经开
		  serverChannel.socket().bind(new InetSocketAddress(port));  //绑定端口
		  
		  serverChannel.register(selector, SelectionKey.OP_ACCEPT);   //服务端关注被链接的事件
		  started = true;
          System.out.println("服务器已启动，端口号：" + port);
	  }catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
	  }
    }

	 public void stop(){
	       started = false;
	 }


	@Override
	public void run() {
		while(started) {
			try {
				selector.select();
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				SelectionKey key = null;
				while(it.hasNext()) {
					key = it.next();
					it.remove();
					try {
					handleInput(key);
					}catch (Exception e) {
						  if(key != null){
	                            key.cancel();
	                            if(key.channel() != null){
	                                key.channel().close();
	                            }
	                        }
					}
				}
			}catch (Exception e) {
              e.printStackTrace();
			}
		}
		
	}
	
	private void handleInput(SelectionKey key) throws Exception{
		if(key.isValid()) {
			//处理新接入的请求消息
			if(key.isAcceptable()) {
				ServerSocketChannel  ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				System.out.println("=========建立链接===========");
				sc.configureBlocking(false); //设置为非阻塞
				sc.register(selector, SelectionKey.OP_READ);
			}
			
			
			if(key.isReadable()) {
				System.out.println("======socket channel 数据准备完成 可以去读取==========");
				SocketChannel sc = (SocketChannel)key.channel();
				//ByteBuffer buffer = ByteBuffer.allocate(1024);
				 //创建ByteBuffer，并开辟一个1M的缓冲区
				ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
				 //读取请求码流，返回读取到的字节数
				int readBytes = sc.read(buffer);
				  //读取到字节，对字节进行编解码
				if(readBytes>0) {
					//将缓冲区当前的limit设置为position,position=0，
		                    // 用于后续对缓冲区的读取操作
					buffer.flip();
					 //根据缓冲区可读字节数创建字节数组
                    byte[] bytes = new byte[buffer.remaining()];
                    //将缓冲区可读字节数组复制到新建的数组中
                    buffer.get(bytes);
                    String message = new String(bytes,"UTF-8");
                    System.out.println("服务器 接收的消息为 ："+message);
                    //处理数据
                    String result = "服务器发送是呀ss"+message ;
                    //发送应答消息
                    doWrite(sc,result);
				}else if(readBytes<0) {
					//链路已经关闭 释放资源
					key.channel();
					sc.close();
				}
			}
		}
	}
   
	//发送应答消息
	private void doWrite(SocketChannel channel,String response) throws Exception{
		byte[] bytes = response.getBytes();
		 //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        //flip操作
        writeBuffer.flip();
        //发送缓冲区的字节数组
        channel.write(writeBuffer);
	}
}
