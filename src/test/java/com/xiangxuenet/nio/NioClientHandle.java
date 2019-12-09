package com.xiangxuenet.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

//nio 客户端处理器

public class NioClientHandle implements Runnable {
    private String host;  //主机号
    private int port;     //端口号
    private volatile boolean started;  //标志可见
    private Selector selector;   //选择器选择事件类型
	private SocketChannel socketChannel;  //监听的通道
    
	
	public NioClientHandle(String host, int port) {
		this.host = host;
		this.port = port;
		try {
			this.selector = Selector.open();     //工厂模式生产一个选择器
			this.socketChannel = SocketChannel.open();
			//设置为非阻塞模式 
			socketChannel.configureBlocking(false);
			started = true;
		}catch (Exception e) {
          e.printStackTrace();
          System.out.println(-1);   //如果出现异常  就退出
		}
	}


   public void stop() {
	   started = false;
   }

	@Override
	public void run() {
		//链接服务器
		try {
			doConnect();
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		while(started) {
			try {
				//阻塞方法，当至少一个注册的事件发生的时候就会继续
				selector.select();
				//获取当前有哪些事件可以使用 所有的事件
				Set<SelectionKey> keys = selector.selectedKeys(); 
				//转换成迭代器
				Iterator<SelectionKey> it = keys.iterator();
				SelectionKey key = null;
				while(it.hasNext()) {
					key = it.next();
					//我们必须首先将处理过的SelectionKey 从选定的键集合中删除
					//如果我们没有删除处理过的键，那么此键仍然会在事件集合中以一个激活的键出现，这会导致我们尝试再次处理
					it.remove();
					try {
						handleInput(key);
					}catch (Exception e) {
                      if(key != null) key.cancel();
                      //
                      if(key.channel()!=null) key.channel().close();
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		//关闭选择器
		if(selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
   
	private void handleInput(SelectionKey key) throws Exception{
		if(key.isValid()) {
			//获得关心当前事件的channel
			SocketChannel sc = (SocketChannel)key.channel();
			//处理链接就绪事件 ，但是三次握手未必成功了，所以需要等待握手完成和判断握手是否成功
			if(key.isConnectable()) {
				/*finishConnect的主要作用就是确认通道连接已建立，
                                                  方便后续IO操作（读写）不会因连接没建立而
                                                   导致NotYetConnectedException异常。*/
				if(sc.finishConnect()) {
					//链接既然已经建立，当然就需要注册读事件
					//写事件一般不需要注册
					socketChannel.register(selector,SelectionKey.OP_READ);
				}else System.exit(-1);
			}
			//处理读事件，也就是当前有数据可读
			if(key.isReadable()) {
				//创建ByteBuffer 并开辟分配一个1k的缓冲区 在内存上
				ByteBuffer buffer = ByteBuffer.allocate(1024);    
				//将通道的数据读取到缓冲区，read方法返回读取到的字节数
				int readBytes = sc.read(buffer);
				if(readBytes > 0) {
					buffer.flip();//重置切换到读模式
					byte[] bytes = new byte[buffer.remaining()];    //有多少的数据可读
					buffer.get(bytes);
					String result = new String(bytes,"UTF-8");
					System.out.println("收到消息："+ result);
				}else if(readBytes<0) {   //小于0 相当于已经分了
					key.cancel();
					sc.close();
				}
			}
		}
	}
	
	//进行连接
	private void doConnect() throws IOException{
		//如果此通道处于阻塞模式，则调用此方法将启动非阻塞连接的操作
		//如果链接马上建立成功，则此方法立马返回true 否则此方法返回false
		//因此我们必须关注链接就绪事件
		//并通过调用finishConnect方法完成连接操作
		//就是没有完成的状态注册一个通道的链接时间 ，（关联上通道）
		//（如果这个连接通道 已经完成了，就不要这个通道了，如果没完成 ，正在进行时就注册到事件选择器上的一个连接事件）
		if(socketChannel.connect(new InetSocketAddress(host,port))) {
		}else {
			socketChannel.register(selector,SelectionKey.OP_CONNECT);
		}
	}
	
	
	 /*写数据对外暴露的API*/
    public void sendMsg(String msg) {
    	//往 channel 中写数据
    	doWrite(socketChannel,msg);
    }


	private void doWrite(SocketChannel sc , String request) {
		byte[] bytes = request.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length); 
		writeBuffer.put(bytes);
		writeBuffer.flip();
		try {
			sc.write(writeBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
