package com.tt.fly.dao.util;

import cn.hutool.json.JSONUtil;
import com.tt.fly.common.enums.Comparator;
import com.tt.fly.common.enums.PricingMethod;
import com.tt.fly.dao.entity.Rule;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author molly
 * @date 2021/11/05
 * @Description coditionStr value should be exactly follow the data structure in json type，multiple conditions should be separate with ";"
 * to see if the value up to level only need compare value with criticalValue, the compare result match comparator is true, or false
 * the final condition is the guarantee rule，the value should take the last rule if it can not match all the rest one.
 */
@Slf4j
public class RuleMatcher {

    public static Rule matchRule(BigDecimal weight, BigDecimal volume, String conditionStr) {

        List<String> conditionList = Arrays.asList(conditionStr.split(";"));

        Rule r = null;
        for(int i = 0; i<conditionList.size(); i++) {
            String condition = conditionList.get(i);
            Rule rule = JSONUtil.toBean(condition, Rule.class);
            if (null == rule) {
                log.error("rule parse error,rulestr={}",condition);
                continue;
            }
            //the last one condition is for all the rest conditions
            if(i == conditionList.size()-1) {
                r = rule;
            }
           Comparator comparator = Comparator.of(rule.getComparator());
            PricingMethod pricingMethod = PricingMethod.of(rule.getPricingMethod());
            BigDecimal criticalVale = rule.getCriticalValue();
            if(null == pricingMethod) {
                log.error("matchRule error,pricingMethod is null");
                continue;
            }
            if (pricingMethod.equals(PricingMethod.WEIGHT) && upToLevel(weight, criticalVale, comparator)) {
                r = rule;
                break;
            }
            if (pricingMethod.equals(PricingMethod.VOLUME) && upToLevel(volume, criticalVale, comparator)) {
                r = rule;
                break;
            }
        }
        return r;
    }

    /**
     * to see if the value upToLevel only need compare value with criticalValue, the compare result match comparator is true, or false
     * @param value
     * @param criticalValue
     * @param comparator
     * @return
     */
    private static boolean upToLevel(@NotNull BigDecimal value, BigDecimal criticalValue, Comparator comparator) {
        if(null == value || null == criticalValue || null == comparator) {
            return false;
        }
        if (comparator.equals(Comparator.LESS_THAN) && value.compareTo(criticalValue) < 0) {
            return true;
        }
        if (comparator.equals(Comparator.MORE_THAN) && value.compareTo(criticalValue) > 0) {
            return true;
        }
        return false;
    }
}
