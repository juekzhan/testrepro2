package com.xiangxuenet.udpnetty.unicast;

import java.util.Random;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class AnswerSideHandler extends SimpleChannelInboundHandler<DatagramPacket>{
   
	/*应答的具体内容从常量字符串数组中取得，由nextQuote方法随机获取*/
	private static final  String[]  DICTIONARY = {
		"古来圣贤皆寂寞，惟有饮者留其名",
		"是气所磅礴，凛烈万古存",
		"青枫江上秋天远，白帝城边古木疏",
		"今古恨，几千般，只应离合是悲欢",
		"自古逢秋悲寂寥，我言秋日胜春朝",
		"出师一表通今古，夜半挑灯更细看",
		"千古风流今在此，万里功名莫放休"
	};
	
	private static Random r = new Random();
	
	private String nextQuote() {
		return DICTIONARY[r.nextInt(DICTIONARY.length-1)];
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
	   //获得请求
		String  req  = packet.content().toString(CharsetUtil.UTF_8);
		System.out.println("接收到请求：" + req);
		if(UdpQuestionSide.QUESTION.equals(req)) {
			String answer  =  UdpAnswerSide.ANSWER  + nextQuote();
			System.out.println("发送应答：" + answer);
			//重新 new 一个DatagramPacket对象，我们通过packet.sender()来获取发送者的消息，重新发送出去
			ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(answer,CharsetUtil.UTF_8), 
					packet.sender()));
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
   
	
	
}
