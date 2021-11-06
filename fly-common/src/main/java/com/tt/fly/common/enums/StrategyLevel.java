package com.tt.fly.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author molly
 * @Date 2021/11/05
 */
public enum StrategyLevel {

    FIRST(1, "firstLevel"),
    SECOND(2, "secondLevel"),
    THIRD(3, "thirdLevel"),
    FOURTH(4, "fourthLevel"),
    FIFTH(5, "fifthLevel");

    private Integer code;
    private String desc;
    private static Map<Integer, StrategyLevel> INIT_MAP = new HashMap<>(1);

    static {
        INIT_MAP.put(FIRST.code, FIRST);
        INIT_MAP.put(SECOND.code, SECOND);
        INIT_MAP.put(THIRD.code, THIRD);
        INIT_MAP.put(FOURTH.code, FOURTH);
        INIT_MAP.put(FIFTH.code, FIFTH);
    }


    StrategyLevel(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

      public static StrategyLevel of(Integer code) {
        if (null == code) {
            return null;
        }
        return INIT_MAP.get(code);
    }
}
