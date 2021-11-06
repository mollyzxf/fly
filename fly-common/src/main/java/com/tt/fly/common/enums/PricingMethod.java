package com.tt.fly.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author molly
 * @date 2021/11/05
 */
public enum PricingMethod {

    WEIGHT(1, "count by weight"),
    VOLUME(2, "count by volume");


    private Integer code;
    private String desc;
    private static  Map<Integer, PricingMethod> INIT_MAP = new HashMap<>(1);

    static {
        INIT_MAP.put(WEIGHT.code, WEIGHT);
        INIT_MAP.put(VOLUME.code, VOLUME);
    }

    PricingMethod(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PricingMethod of(Integer code){

        if (null == code) {
            return null;
        }
        return INIT_MAP.get(code);
    }

}
