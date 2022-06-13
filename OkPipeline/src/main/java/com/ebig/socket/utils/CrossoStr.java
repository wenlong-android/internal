package com.ebig.socket.utils;

import com.ebig.utils.HexUtils;



import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class CrossoStr {
    public static String getLcdCmd(String mode,String content){
        byte[] bytes= new byte[0];
        try {
            bytes = content.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String hex=HexUtils.bytesToHex(bytes);// Hex.toHexString(bytes);
        int len=hex.length()/2;
        String hexlen=HexUtils.int2Hex(len);
        System.out.println( "["+mode+" "+hexlen+" "+hex+"]");
        return mode+hexlen+hex;
    }
private byte[] getChinise(String content){
    char[] chars = content.toCharArray();
    //使用utf-8编码字符集
    Charset charset = Charset.forName("utf-8");
    CharBuffer charBuffer = CharBuffer.allocate(chars.length);
    charBuffer.put(chars);
    charBuffer.flip();
    //字符编码为字节数组
    ByteBuffer byteBuffer = charset.encode(charBuffer);
    byte[] charToBytes = byteBuffer.array();
    return charToBytes;
}

//    public static String str2HexStr(String str) {
//        char[] chars = "0123456789ABCDEF".toCharArray();
//        StringBuilder sb = new StringBuilder("");
//        byte[] bs = str.getBytes();
//        int bit;
//        for (int i = 0; i < bs.length; i++) {
//            bit = (bs[i] & 0x0f0) >> 4;
//            sb.append(chars[bit]);
//            bit = bs[i] & 0x0f;
//            sb.append(chars[bit]);
//            // sb.append(' ');
//        }
//        return sb.toString().trim();
//    }

}
