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
 * @Description the 1st rule reject weights exceeds 50kg
 *the num of condition and the price could be dynamic configuration but should be exactly in order
 *and not support for comparator dynamic configuration
 */
@Component
@Slf4j
public class FirstLevelStrategy implements BasicStrategy{


    @Override
    public BigDecimal countFeight(BigDecimal weight, BigDecimal volume, Rule rule) {
        //reject weights exceeds 50kg
        StrategyLevel strategyLevel = StrategyLevel.of(rule.getStrategyLevel());
        if (!strategyLevel.equals(StrategyLevel.FIRST)) {
            throw new RuntimeException(StrUtil.format("using FirstLevelStrategy in wrong,volume={},weight={}",volume,weight));
        }
        log.warn("com.tt.fly.service.ParcelServiceImpl.countFreight failed, weight is over 50kg");
        return null;
    }
}
