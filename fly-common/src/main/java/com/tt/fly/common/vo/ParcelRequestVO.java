package com.tt.fly.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author molly
 * @date 2021/11/05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParcelRequestVO implements Serializable {
    /**
     * weight(kg)
     */
    private BigDecimal weight;
    /**
     * height(cm)
     */
    @NonNull
    private BigDecimal height;
    /**
     * width(cm)
     */
    @NonNull
    private BigDecimal width;
    /**
     * length(cm)
     */
    @NonNull
    private BigDecimal length;
    /**
     * voucherCode
     */
    private String  voucherCode;
}
