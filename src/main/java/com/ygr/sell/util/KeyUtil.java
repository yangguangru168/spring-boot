package com.ygr.sell.util;

import java.util.Random;

public class KeyUtil {

    public static synchronized String randomKey(){
        Random random = new Random();
        Integer randomKey = random.nextInt(900000)+200000;
        return System.currentTimeMillis()+String.valueOf(randomKey);
    }
}
