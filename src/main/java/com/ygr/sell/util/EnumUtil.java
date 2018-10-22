package com.ygr.sell.util;

import com.ygr.sell.enums.CodeEnum;

public class EnumUtil {

    public static <T extends CodeEnum> T getCode(Integer code,Class<T> enumClass){
        for (T each: enumClass.getEnumConstants()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }
}
