package com.tt.fly.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author molly
 * @date 2021/11/05
 */

public enum Comparator {

    LESS_THAN(1, "小于"),
    MORE_THAN(2, "大于");

    private Integer code;
    private String desc;

    private static Map<Integer, Comparator> INIT_MAP = new HashMap<>(1);
    static {
        INIT_MAP.put(LESS_THAN.code, LESS_THAN);
        INIT_MAP.put(MORE_THAN.code, MORE_THAN);
    }

    Comparator(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Comparator of(Integer code) {
        if (null == code) {
            return null;
        }
        return INIT_MAP.get(code);
    }
}
