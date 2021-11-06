package com.tt.fly.service;


import com.tt.fly.common.vo.ParcelRequestVO;
import com.tt.fly.dao.entity.Parcel;

import java.math.BigDecimal;

/**
 * @author molly
 * @date 2021/11/4
 */
public interface ParcelService {

    /**
     * only for weight under 50kg
     * @param parcel
     * @param voucherCode
     * @return
     */
    BigDecimal countFreightUnderFiftyKg(Parcel parcel, String voucherCode);
}
