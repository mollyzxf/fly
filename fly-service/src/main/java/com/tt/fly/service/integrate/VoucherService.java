package com.tt.fly.service.integrate;

import java.util.Map;
/**
 * @author molly
 * @Date 2021/11/05
 */
public interface VoucherService {

    Map<String,Object> getDiscountInfo(String voucherCode);
}
