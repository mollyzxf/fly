package com.tt.fly.service.strategy;

import com.tt.fly.dao.entity.Rule;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author molly
 * @date 2021/11/05
 */
public interface BasicStrategy {

    BigDecimal countFeight(BigDecimal weight , BigDecimal volume , Rule rule);
}
