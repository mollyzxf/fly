package com.tt.fly.service.integrate;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.tt.fly.common.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author molly
 * @Date 2021/11/05
 */

@Service
@Slf4j
public class VoucherServiceImpl implements VoucherService{
    @Value("${voucher.httpUrl}")
    private  String voucherHttpurl;

    @Override
    public Map<String, Object> getDiscountInfo(String voucherCode) {
        if(StrUtil.isBlank(voucherCode)) {
            log.warn("getDiscountInfo failed,param voucherCode is null");
            return null;
        }
        String httpUrl = StrUtil.format(voucherHttpurl,voucherCode);
        String result = HttpClientUtil.doGet(httpUrl);
        if (StrUtil.isBlank(result)) {
            return null;
        }
        return JSONUtil.parseObj(result);
    }




}
