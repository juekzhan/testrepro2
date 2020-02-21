package com.xiangxuenet.nettyhttp.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class BusiHandler extends ChannelInboundHandlerAdapter {
   
	private void send(ChannelHandlerContext ctx,String context,HttpResponseStatus status) {
		FullHttpResponse  respone = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status
				,Unpooled.copiedBuffer(context,CharsetUtil.UTF_8));//把信息包装成 response比较 好 
		respone.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain ; charset=UTF-8");//header头部
		ctx.writeAndFlush(respone).addListener(ChannelFutureListener.CLOSE); // 发送到Flush ，并且关闭
	}
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      String result = "";
      FullHttpRequest httpRequest = (FullHttpRequest) msg; //获得请求的 http信息
      System.out.println("http请求头" + httpRequest.headers());
      try {
    	  //获取路径
    	  String path = httpRequest.uri();
    	  //获取body
    	  String body = httpRequest.content().toString(CharsetUtil.UTF_8);
    	  //获取请求方法
          HttpMethod method=httpRequest.method();
          //如果不是这个路径，就直接返回错误
          if(!"/test".equalsIgnoreCase(path)) {
        	  result = "非法请求！" + path;
        	  send(ctx,result,HttpResponseStatus.BAD_REQUEST);
              return;
          }
          System.out.println("接收到:" + method + "请求");
          //如果是GET请求
          if(HttpMethod.GET.equals(method)) {
        	  //接收到的消息，做业务逻辑处理
        	  System.out.println("body:" + body);
        	  result = "GET请求，应答：" + RespConstant.getNews();
        	  send(ctx, result, HttpResponseStatus.OK);
        	  return;
          }
          //如果是 其他类型的请求 ，如 post
          if(HttpMethod.POST.equals(method)) {
        	  //接收到 的消息，做业务 逻辑处理
        	  return;
          }
      }catch (Exception e) {
        System.out.println("处理请求失败");
        e.printStackTrace();
      }finally {
		httpRequest.release();
	}
	}
  
	
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("链接的客户端地址:" + ctx.channel().remoteAddress());
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
   
}
