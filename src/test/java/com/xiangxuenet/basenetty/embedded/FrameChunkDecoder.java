package com.xiangxuenet.basenetty.embedded;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

public class FrameChunkDecoder extends ByteToMessageDecoder {
    private final int maxFrameSize;
    
    //指定将要产生的帧的最大允许大小
    public FrameChunkDecoder(int maxFrameSize) {
        this.maxFrameSize = maxFrameSize;
    }
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
			throws Exception {
        int reabableBytes = in.readableBytes();  //writeIndex - readIndex
        if(reabableBytes > maxFrameSize) {
        	//如果该帧超出 允许的大小，则丢弃它并抛出一个TooLongFrameException
        	in.clear();
        	throw new TooLongFrameException();
        }
        //.....否则 从ByteBuf中读取一个新帧
        ByteBuf buf = in.readBytes(reabableBytes);
        out.add(buf);
	}

}
