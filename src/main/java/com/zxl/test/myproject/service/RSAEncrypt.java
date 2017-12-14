package com.zxl.test.myproject.service;

import org.springframework.util.Base64Utils;

import java.util.Base64;

/**
 * Created by Alex on 16/10/22 下午2:01.
 */
public class RSAEncrypt {
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6',
        '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String byteToString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
            sb.append(HEX_CHAR[(data[i] & 0x0f)]);
            if (i < data.length - 1) {
                sb.append(' ');
            }
        }
        return sb.toString();

    }

//    public static void main(String[] args) {
//        String result = Integer.toHexString(26);
//        System.out.println(result);
//        System.out.println(byteToString("Tom".getBytes()));
//        System.out.println(new String(Base64Utils.encode("Tom".getBytes())));
//
//        String bas64 = Base64.getEncoder().encodeToString("Tom".getBytes());
//        System.out.println(bas64);
//    }
}
