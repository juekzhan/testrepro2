package com.xiangxuenet.nettyhttp.client;

import java.net.URI;

import com.xiangxuenet.nettyhttp.server.HttpServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpVersion;

public class HttpClient {
  @SuppressWarnings("unused")
private static final boolean SSL = false;
  
  public void connect(String host,int port) throws Exception{
	  EventLoopGroup workerGroup = new NioEventLoopGroup();
	  
	  try {
		  Bootstrap b = new Bootstrap();
		  b.group(workerGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true)//选项保持长连接
		 .handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				//客户端接收到的是httpResponse
				//所以要使用HttpResPonseDecoder进行解码
				ch.pipeline().addLast(new HttpClientCodec()); //对相应进行解码  这个继承了解码器的东西
				ch.pipeline().addLast("aggregator",new HttpObjectAggregator(512*1024));
				ch.pipeline().addLast("decompressor",new HttpContentDecompressor());
				ch.pipeline().addLast(new HttpClientInboundHandler());
			}
		});
		  
		 ChannelFuture  f = b.connect(host,port).sync();
		  
		 URI uri = new URI("/test");
		 String msg = "Hello";
		 //构建http请求
		 DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
						  HttpMethod.GET,
						  uri.toASCIIString(),
						  Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));
		 //构建http头
		 request.headers().set(HttpHeaderNames.HOST,host);
		 request.headers().set(HttpHeaderNames.CONNECTION,
				               HttpHeaderValues.KEEP_ALIVE);
		 request.headers().set(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
		 
		 //发送http请求
		 f.channel().write(request);
		 f.channel().flush();
		 f.channel().closeFuture().sync();
	  }finally {
		  workerGroup.shutdownGracefully().sync();
	  }
  }
  
  public static void main(String[] args) throws Exception {
	HttpClient client = new HttpClient();
	client.connect("127.0.0.1", HttpServer.port);
}
}
