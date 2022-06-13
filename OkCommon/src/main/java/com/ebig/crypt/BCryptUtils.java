package com.ebig.crypt;

public class BCryptUtils {
    /**
     * @param raw 明文
     * @param encode 密文
     * @return 是否匹对
     */
    public static boolean matche(String raw,String encode){
       return new BCryptPasswordEncoder().matches(raw, encode);
    }
}
