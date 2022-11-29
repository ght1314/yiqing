package com.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.dto.VistorDto;
import com.demo.entity.VisitorEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 来访客人登记表 Mapper 接口
 */
public interface VisitorMapper extends BaseMapper<VisitorEntity> {


    //查询数据，分页
    IPage<VistorDto>  queryvisitorByParam(@Param("page") Page<VistorDto> page,
                                          @Param("keyword")String keyword);

    VistorDto queryVisitorById(Integer visId);

    @Transactional(propagation = Propagation.REQUIRED)
    void  addVistor(VistorDto vistorDto);

    @Transactional(propagation = Propagation.REQUIRED)
    void updateVistor(VistorDto vistorDto);

    @Transactional(propagation = Propagation.REQUIRED)
    void deleteVistor(Integer[] ids);

}
