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
 * @Description the forth priority rule when volume  less than 2500 cm3, cost = PHP 0.04 * Volume
 *the num of condition and the price could be dynamic configuration but should be exactly in order
 *and not support for comparator dynamic configuration
 */
@Component
@Slf4j
public class FourthLevelStrategy implements BasicStrategy{

    @Override
    public BigDecimal countFeight(BigDecimal weight, BigDecimal volume, Rule rule) {
        StrategyLevel strategyLevel = StrategyLevel.of(rule.getStrategyLevel());
        if (!strategyLevel.equals(StrategyLevel.FOURTH)) {
            throw new RuntimeException(StrUtil.format("using SecondLevelStrategy in wrong,weight={}",weight));
        }
        return volume.multiply(rule.getPrice());
    }
}
