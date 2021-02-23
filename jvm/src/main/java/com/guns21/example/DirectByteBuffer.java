package com.guns21.example;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @see https://www.cnblogs.com/duanxz/p/6089485.html
 */
public class DirectByteBuffer {

    public static void main(String[] args) {

    }
    /**
     * @VM args:-XX:MaxDirectMemorySize=40m -verbose:gc -XX:+PrintGCDetails
     * -XX:+DisableExplicitGC //增加此参数一会儿就会内存溢出java.lang.OutOfMemoryError: Direct buffer memory
     */
    public static void TestDirectByteBuffer() {
        List<ByteBuffer> list = new ArrayList<ByteBuffer>();
        while(true) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1 * 1024 * 1024);
            //list.add(buffer);
        }
    }
}
