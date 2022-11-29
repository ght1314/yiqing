package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.dto.VistorDto;
import com.demo.entity.VisitorEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.utils.Result;

/**
 * 来访客人登记表 服务类
 */
public interface VisitorService extends IService<VisitorEntity> {

    IPage<VistorDto> queryVistorByParam(Integer currentPage, Integer limit, String keyword);

    Result addVistor(VistorDto vistorDto);

    Result deleteVistors(Integer[] ids);

    Result updateVistor(VistorDto vistorDto);

    IPage<VistorDto> queryVistorByParam();

}
