package com.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.mapper.AddressMapper;
import com.demo.service.AddressService;
import com.demo.dto.AddressDto;
import com.demo.entity.AddressEntity;
import com.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地址表  服务实现类
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressEntity> implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public IPage<AddressDto> queryAddressByParam(Integer currentPage, Integer limit, String keyword) {
        Page<AddressDto> page = new Page<>(currentPage, limit);
        return addressMapper.queryAddressByParam(page, keyword);
    }

    @Override
    public Result addAddress(AddressDto addressDto) {
        addressMapper.addAddress(addressDto);
        return Result.ok("添加地址成功");
    }

    @Override
    public Result updateAddress(AddressDto addressDto) {
        addressMapper.updateAddress(addressDto);
        return Result.ok("更新地址成功！");
    }

    @Override
    public Result deleteAddress(Integer[] ids) {
        addressMapper.deleteAddress(ids);
        return Result.ok();
    }

    @Override
    public IPage<AddressDto> queryAddressByParam() {
        Page<AddressDto> page = new Page<>();
        return addressMapper.queryAddressByParam(page, null);
    }

    @Override
    public int insertBatchAddress(List<AddressDto> addressList) {
        int batchResident = addressMapper.insertBatchResident(addressList);
        return batchResident;
    }
}
