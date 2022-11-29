package com.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.dto.RecordDto;
import com.demo.entity.RecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  Mapper 接口
 */
public interface RecordMapper extends BaseMapper<RecordEntity> {

    //查询数据，分页
    IPage<RecordDto> queryRecordByParam(@Param("page") Page<RecordDto> page,
                                        @Param("keyword")String keyword);
    //    新增数据,业主记录
    @Transactional(propagation = Propagation.REQUIRED)
    void addRecordRes(RecordDto recordDto);
    //新增数据，访客记录
    @Transactional(propagation = Propagation.REQUIRED)
    void addRecordVis(RecordDto recordDto);

    //根据ID查询业主信息
    RecordDto queryRecordById(String recId);

    //    修改操作
    @Transactional(propagation = Propagation.REQUIRED)
    void updateRecordRes(RecordDto recordDto);

    @Transactional(propagation = Propagation.REQUIRED)
    void updateRecordVis(RecordDto recordDto);

    //    删除数据通过ResID（一条或多条）实际是更新操作
    @Transactional(propagation = Propagation.REQUIRED)
    void deleteRecordVis(Integer[] ids);

    @Transactional(propagation = Propagation.REQUIRED)
    void deleteRecord(Integer[] ids);

    List<RecordDto> queryRecCount();


    List<RecordDto> queryRecCountByDate(@Param("beginTime") String beginTime,
                                        @Param("endTime") String endTime);

}
