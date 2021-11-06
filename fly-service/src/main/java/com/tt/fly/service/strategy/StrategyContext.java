package com.tt.fly.service.strategy;

import cn.hutool.core.util.StrUtil;
import com.tt.fly.common.enums.StrategyLevel;
import com.tt.fly.dao.entity.Rule;
import com.tt.fly.dao.util.RuleMatcher;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author molly
 * @date 2021/11/05
 * @Description the num of condition and the price could be dynamic configuration but should be exactly in order
 * ,and not support for comparator dynamic configuration.
 */
@Component
@NoArgsConstructor
@Slf4j
public class StrategyContext {
    @Value("${strategy.conditions}")
    private String conditionStr;


    public BigDecimal invoke(BigDecimal weight, BigDecimal volume){
        if (null == weight || null == volume) {
            return null;
        }
        Rule rule = RuleMatcher.matchRule(weight, volume, conditionStr);

        if (null == rule) {
            log.error(StrUtil.format("weight={},volume={} not match any rule", weight, volume));
            return BigDecimal.ZERO;
        }
        BasicStrategy strategy = null;
        StrategyLevel strategyLevel = StrategyLevel.of(rule.getStrategyLevel());
        if (null == strategyLevel) {
            log.error("weight={},volume={} strategeLevel is null");
            return BigDecimal.ZERO;
        }
        if (strategyLevel.equals(StrategyLevel.FIRST)) {
            strategy = new FirstLevelStrategy();
        }
        if (strategyLevel.equals(StrategyLevel.SECOND)) {
            strategy = new SecondLevelStrategy();
        }
        if (strategyLevel.equals(StrategyLevel.THIRD)) {
            strategy = new ThirdLevelStrategy();
        }
        if (strategyLevel.equals(StrategyLevel.FOURTH)) {
            strategy = new FourthLevelStrategy();
        }
        if (strategyLevel.equals(StrategyLevel.FIFTH)) {
            strategy = new FifthLevelStrategy();
        }
        if (null == strategy) {
            return null;
        }
        return strategy.countFeight(weight , volume , rule);
    }
}
