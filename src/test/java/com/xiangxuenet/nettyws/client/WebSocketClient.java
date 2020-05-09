package com.xiangxuenet.nettyws.client;

import java.net.URI;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * 
 * @ClassName: WebSocketClient  
 * @Package :com.xiangxuenet.nettyws.client
 * @Description: 这是WebSocket客户端的示例。
    要运行此示例，需要兼容的WebSocket服务器。
          因此，可以通过运行WebSocketServer来启动WebSocket服务器，
 * @Author: shuling.zhan
 */
public class WebSocketClient {

	static final String URL = System.getProperty("url", "ws://127.0.0.1:8080/websocket");
	
	static final String SURL = System.getProperty("url","wss://127.0.0.1:8443/websocket");
	
	public static void main(String[] args) throws Exception {
		URI uri = new URI(URL); 
        
		String scheme = uri.getScheme() == null? "ws" :uri.getScheme();
		
		final String host = uri.getHost() == null? "127.0.0.1" :uri.getHost();
		
		final int port = uri.getPort();
		
		if(!"ws".equalsIgnoreCase(scheme) && !"wss".equalsIgnoreCase(scheme)) {
			System.err.println("Only WS(S) is supported.");
			return;
		}
		final boolean  ssl = "wss".equalsIgnoreCase(scheme);
		final SslContext sslCtx;
		if(ssl) {
			sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
		}else {
			sslCtx = null;
		}
		
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			
			// Connect with V13 (RFC 6455 aka HyBi-17). You can change it to V08 or V00.
			// 用RFC 协议
            // If you change it to V00, ping is not supported and remember to change
            // HttpResponseDecoder to WebSocketHttpResponseDecoder in the pipeline.
			//final WebSocketClientHandler handler
			
			Bootstrap b = new Bootstrap();
			
			
		}finally {
			group.shutdownGracefully();
		}
		
	}

}
