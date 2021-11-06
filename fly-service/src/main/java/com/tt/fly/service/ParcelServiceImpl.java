package com.tt.fly.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.tt.fly.dao.converter.ParcelConverter;
import com.tt.fly.dao.entity.Parcel;
import com.tt.fly.common.vo.ParcelRequestVO;
import com.tt.fly.service.integrate.VoucherService;
import com.tt.fly.service.strategy.StrategyContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
/**
 * @author molly
 * @Date 2021/11/05
 */

@Service
@Slf4j
public class ParcelServiceImpl implements ParcelService {
    @Autowired
    private VoucherService voucherService;
    @Autowired
    private ParcelConverter parcelConverter;
    @Autowired
    private StrategyContext strategyContext;

    @Override
    public BigDecimal countFreightUnderFiftyKg(Parcel parcel, String voucherCode) {

        //get feight
        BigDecimal feight = strategyContext.invoke(parcel.getWeight() , parcel.volume());
        if (null == feight || feight.compareTo(BigDecimal.ZERO) == 0) {
            return feight;
        }

        //get discount
        BigDecimal discount = BigDecimal.ZERO;
        if (StrUtil.isNotBlank(voucherCode)) {
            Map<String, Object> map = voucherService.getDiscountInfo(voucherCode);
            log.info("com.tt.fly.service.ParcelServiceImpl.countFreight discount infor={}", JSONUtil.toJsonStr(map));

            if (map != null && map.get("discount") != null) {
                String expiryDateStr = map.get("expiry") == null ? null : (String) map.get("expiry");
                if (null != expiryDateStr) {
                    Date expiryDate = DateUtil.parse(expiryDateStr, "yyyy-MM-dd");
                    if (expiryDate.after(new Date())) {
                        discount = (BigDecimal) map.get("discount");
                    } else {
                        log.warn("com.tt.fly.service.ParcelServiceImpl.countFreight code={} discount expired", map.get("code"));
                    }
                }
            }
        }
       return feight.subtract(discount);
    }
}
