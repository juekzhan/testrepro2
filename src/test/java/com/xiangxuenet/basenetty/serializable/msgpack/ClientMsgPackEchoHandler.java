package com.xiangxuenet.basenetty.serializable.msgpack;

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class ClientMsgPackEchoHandler extends SimpleChannelInboundHandler<ByteBuf> {
	private final int sendNumber;

	public ClientMsgPackEchoHandler(int sendNumber) {
		this.sendNumber = sendNumber;
	}

	private AtomicInteger counter = new AtomicInteger(0);

	// 客户端读取到网络数据后的处理
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		System.out.println("client Acceptp["+msg.toString(CharsetUtil.UTF_8) +"]  and counter is："
				+  counter.incrementAndGet());

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		User[] users = makeUsers();
		for(User user : users) {
			System.out.println("Send user" +  user);
			ctx.write(user);
		}
		ctx.flush();
	}
    
	
	private User[] makeUsers() {
		User[] users = new  User[sendNumber];
		User user = null;
		for(int i = 0;i < sendNumber ; i++) {
			user  = new  User();
			user.setAge(i);
			String userName = "ABCDEFG--->" + i;
			user.setUserName(userName);
			user.setId("No:"  + (sendNumber - i));
			user.setUserContact(new UserContact(userName + "juek@.com","133"));
			users[i] = user;
		}
		return users;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		 cause.printStackTrace();
	     ctx.close();
	}
	
	
}
