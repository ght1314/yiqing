package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.dto.AddressDto;
import com.demo.entity.AddressEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Mapper 接口
 */
public interface AddressMapper extends BaseMapper<AddressEntity> {
    /**
     * 通过参数 查询地址ID
     *
     * @param house
     * @param unit
     * @return
     */
    Integer queryVisIdByparmas(@Param("house") Integer house, @Param("unit") Integer unit);

    IPage<AddressDto> queryAddressByParam(@Param("page") Page<AddressDto> page,
                                          @Param("keyword") String keyword);

    @Transactional(propagation = Propagation.REQUIRED)
    void addAddress(AddressDto addressDto);

    @Transactional(propagation = Propagation.REQUIRED)
    void updateAddress(AddressDto addressDto);

    @Transactional(propagation = Propagation.REQUIRED)
    void deleteAddress(Integer[] ids);

    //根据id查找
    AddressDto queryAddressById(Integer addId);

    int insertBatchResident(@Param("addressList") List<AddressDto> addressList);
}
