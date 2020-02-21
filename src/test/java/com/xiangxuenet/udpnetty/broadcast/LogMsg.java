package com.xiangxuenet.udpnetty.broadcast;

import java.net.InetSocketAddress;

//日志实体类
public class LogMsg {
	public static final byte SEPARATOR = (byte) ':';
	/* 源的 InetSocketAddress */
	private final InetSocketAddress source;
	/* 消息内容 */
	private final String msg;
	/* 消息id */
	private final long msgId;
	/* 消息发送的时间 */
	private final long time;

	public LogMsg(String msg) {
		this(null, msg, -1, System.currentTimeMillis());
	}

	public LogMsg(InetSocketAddress source, String msg, long msgId) {
		this(source, msg, msgId, System.currentTimeMillis());
	}

	public LogMsg(InetSocketAddress source, String msg, long msgId, long time) {
		this.source = source;
		this.msg = msg;
		this.msgId = msgId;
		this.time = time;
	}

	public InetSocketAddress getSource() {
		return source;
	}

	public String getMsg() {
		return msg;
	}

	public long getMsgId() {
		return msgId;
	}

	public long getTime() {
		return time;
	}

}
