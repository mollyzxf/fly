package com.tt.fly.api.web;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.tt.fly.common.enums.StrategyLevel;
import com.tt.fly.common.util.Response;
import com.tt.fly.common.vo.ParcelRequestVO;
import com.tt.fly.dao.converter.ParcelConverter;
import com.tt.fly.dao.entity.Parcel;
import com.tt.fly.dao.entity.Rule;
import com.tt.fly.dao.util.RuleMatcher;
import com.tt.fly.service.ParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


/**
 * @author molly
 * @Date 2021/11/05
 */
@RestController
@Slf4j
public class ParcelController {

    @Autowired
    private ParcelService parcelService;
    @Value("${strategy.conditions}")
    private String conditionStr;
    @Autowired
    private ParcelConverter parcelConverter;


    @PostMapping("/parcel/charge/count")
    public Object countCharge(@RequestBody ParcelRequestVO parcelRequestVO){
        Parcel parcel = parcelConverter.toEntity(parcelRequestVO);

       Rule rule = RuleMatcher.matchRule(parcel.getWeight(), parcel.volume(), conditionStr);
        StrategyLevel strategyLevel = StrategyLevel.of(rule.getStrategyLevel());
        if (null == strategyLevel) {
            log.error("countCharge error, strategeLevel is null,params={}", JSONUtil.toJsonStr(parcelRequestVO));
            return Response.fail(501, "system error");
        }
       if (strategyLevel.equals(StrategyLevel.FIRST)) {
          return Response.fail(500, StrUtil.format("weight exceeds {}kg,rejected", parcel.getWeight()));
       }
       BigDecimal data = parcelService.countFreightUnderFiftyKg(parcel, parcelRequestVO.getVoucherCode());
        return Response.ok(data);
    }

}
