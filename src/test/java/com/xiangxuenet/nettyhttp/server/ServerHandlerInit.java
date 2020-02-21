package com.xiangxuenet.nettyhttp.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;

public class ServerHandlerInit extends ChannelInitializer<Channel> {
	
	private final SslContext sslContext;
	
    public ServerHandlerInit(SslContext sslContext) {
		this.sslContext = sslContext;
	}

	//---->本地程序 为入站
	@Override
	protected void initChannel(Channel ch) throws Exception {
		//接收 HTTP请求，返回http相应  对http  相应的请求传过去 
		//处理http服务的关键 的 handler
		if(sslContext != null) {
			ch.pipeline().addLast(sslContext.newHandler(ch.alloc()));
		}
		ch.pipeline().addLast("encoder", new HttpResponseEncoder());//(对应答进行一个编码)  //(编码器)（编码成101011010）入站的先进行编码，http提供编码器，（其实就很解决粘包，拆包问题一样）
		ch.pipeline().addLast("decoder", new HttpRequestDecoder()); //（对请求一个解码）(解码器)  再解码 ，成 明文
        //ch.pipeline().addLast(new HttpServerCodec()); //同时 编码和解码
		ch.pipeline().addLast("aggregator",
                new HttpObjectAggregator(10*1024*1024)); //多个 http报文组合 到一起 ，形成一个对象（聚合）
		ch.pipeline().addLast("compressor",new HttpContentCompressor());
		ch.pipeline().addLast(new BusiHandler());
		
	}
    
	
}
