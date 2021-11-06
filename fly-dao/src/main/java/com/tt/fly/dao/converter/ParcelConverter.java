package com.tt.fly.dao.converter;

import com.tt.fly.dao.entity.Parcel;
import com.tt.fly.common.vo.ParcelRequestVO;
import org.mapstruct.Mapper;

/**
 * @author molly
 * @date 2021/11/4
 */

@Mapper(componentModel = "spring")
public interface ParcelConverter {

    public Parcel toEntity(ParcelRequestVO parcelRequestVO);
}
