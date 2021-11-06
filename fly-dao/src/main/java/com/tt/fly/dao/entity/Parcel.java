package com.tt.fly.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author molly
 * @date 2021/11/05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcel implements Serializable {
    /**
     * weight(kg)
     */
    private BigDecimal weight;
    /**
     * height(cm)
     */
    private BigDecimal height;
    /**
     * width(cm)
     */
    private BigDecimal width;
    /**
     * length(cm)
     */
    private BigDecimal length;


    public  BigDecimal volume(){
        if(length != null && width != null && height != null) {
            return this.length.multiply(this.width).multiply(this.height);
        }
        return BigDecimal.ZERO;
    }
}
