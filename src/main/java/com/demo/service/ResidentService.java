package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.dto.ResidentDto;
import com.demo.entity.ResidentEntity;
import com.demo.utils.Result;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务类
 */
public interface ResidentService extends IService<ResidentEntity> {

    IPage<ResidentDto> residentsByParams(Integer currentPage, Integer limit, String keyword, String house, String unit);


    @Transactional(propagation = Propagation.REQUIRED)
    Result addResident(ResidentDto resident);

    ResidentDto queryResidentById(int resId);

    @Transactional(propagation = Propagation.REQUIRED)
    Result updateResident(ResidentDto residentDto);

    @Transactional(propagation = Propagation.REQUIRED)
    Result deleteResidents(Integer[] ids);

    /**
     * 获取用户总数
     *
     * @return
     */
    Result queryResCountl();

    /**
     * 高危业主信息
     */
    IPage<ResidentDto> queryResPeril(Integer currentPage, Integer limit);

    Result queryResCountByDate();

    /**
     * 批量添加数据
     */
    int insertBatchResident(List<ResidentDto> residentDtos);
}
