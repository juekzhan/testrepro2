package com.xiangxueJVM.ch5.oom;

import java.nio.ByteBuffer;

//-XX:MaxDirectMemorySize=100m
// 限制最大直接内存大小100m
//直接内存溢出
//java.lang.OutOfMemoryError: Direct buffer memory
public class DirectOom {
   @SuppressWarnings("unused")
public static void main(String[] args) {
	   //直接分配128M的直接内存(100M)
       ByteBuffer bb = ByteBuffer.allocateDirect(128*1024*1204);
}
}
