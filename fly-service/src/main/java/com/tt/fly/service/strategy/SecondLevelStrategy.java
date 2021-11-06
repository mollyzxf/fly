package com.tt.fly.service.strategy;

import cn.hutool.core.util.StrUtil;
import com.tt.fly.common.enums.StrategyLevel;
import com.tt.fly.dao.entity.Rule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author molly
 * @date 2021/11/05
 * @Description  the second priority rule when weight exceeds 10kg, cost = PHP 20 * Weight (kg)
 *the num of condition and the price could be dynamic configuration but should be exactly in order
 *and not support for comparator dynamic configuration
 */
@Component
@Slf4j
public class SecondLevelStrategy implements BasicStrategy{


    @Override
    public BigDecimal countFeight(BigDecimal weight, BigDecimal volume, Rule rule) {
        // second Priority rule when weight exceeds 10kg, cost = PHP 20 * Weight (kg)
        StrategyLevel strategyLevel = StrategyLevel.of(rule.getStrategyLevel());
        if (!strategyLevel.equals(StrategyLevel.SECOND)) {
            throw new RuntimeException(StrUtil.format("using SecondLevelStrategy in wrong,weight={}",weight));
        }
        return  weight.multiply(rule.getPrice());
    }
}
