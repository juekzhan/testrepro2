package com.xiangxuenet.nettyws.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.util.CharsetUtil;

public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {
    
	//负责和服务器握手
	private final WebSocketClientHandshaker handshaker;
	
	//握手的结果
    private ChannelPromise handshakeFuture;
	
    
    public WebSocketClientHandler(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }

    public ChannelFuture handshakeFuture() {
        return handshakeFuture;
    }
    
    
    
    
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		Channel ch = ctx.channel();
		//握手未完成，完成握手
		if(!handshaker.isHandshakeComplete()) {
			try {
				 handshaker.finishHandshake(ch, (FullHttpResponse) msg);
	                System.out.println("WebSocket Client connected!");
	                handshakeFuture.setSuccess();
			}catch (Exception e) {
			   System.out.println("WebSocket Client failed to connect");
	           handshakeFuture.setFailure(e);
			}
			return;
		}
		
		//握手已经完成，升级为了websocket，不应该再收到 http 报文
		if(msg instanceof FullHttpResponse) {
			FullHttpResponse response = (FullHttpResponse) msg;
			throw new IllegalStateException(
                    "Unexpected FullHttpResponse (getStatus=" + response.status() +
                            ", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
		}
	}
    
	//当前Handler被添加到ChannelPipeline时，
    // new出握手的结果的实例，以备将来使用
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		handshakeFuture = ctx.newPromise();
	}
    
	
	//通道建立，进行握手
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		handshaker.handshake(ctx.channel());
	}
	
	//通道关闭
	   @Override
	    public void channelInactive(ChannelHandlerContext ctx) {
	        System.out.println("WebSocket Client disconnected!");
	    }
	
    
}
