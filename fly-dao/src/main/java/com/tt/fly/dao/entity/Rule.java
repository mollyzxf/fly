package com.tt.fly.dao.entity;

import com.tt.fly.common.enums.Comparator;
import com.tt.fly.common.enums.PricingMethod;
import com.tt.fly.common.enums.StrategyLevel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author molly
 * @date 2021/11/05
 */
@Data
public class Rule {

    /**
     * @see StrategyLevel
     */
    private Integer strategyLevel;

    private String ruleName;

    /**
     * @see Comparator
     */
    private Integer comparator;

    private BigDecimal criticalValue;

    private String criticalValueUnit;

    private BigDecimal price;

    private String priceUnit;

    /**
     * @see PricingMethod
     */
    private Integer pricingMethod;
}
