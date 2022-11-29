package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.dto.AddressDto;
import com.demo.entity.AddressEntity;
import com.demo.utils.Result;

import java.util.List;

/**
 * 服务类
 */
public interface AddressService extends IService<AddressEntity> {

    IPage<AddressDto> queryAddressByParam(Integer currentPage, Integer limit, String keyword);

    Result addAddress(AddressDto addressDto);

    Result updateAddress(AddressDto addressDto);

    Result deleteAddress(Integer[] ids);

    IPage<AddressDto> queryAddressByParam();

    int insertBatchAddress(List<AddressDto> addressList);
}
