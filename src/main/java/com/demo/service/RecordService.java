package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.dto.RecordDto;
import com.demo.entity.RecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.utils.Result;

import java.util.List;

/**
 *  服务类
 */
public interface RecordService extends IService<RecordEntity> {

    IPage<RecordDto> queryRecordByParam(Integer currentPage, Integer limit, String keyword);

    Result addRecord(RecordDto recordDto);

    //根据ID查询业主信息
    RecordDto queryRecordById(String recId);

    //    修改操作
    Result updateRecord(RecordDto recordDto);
        //    删除数据通过ResID（一条或多条）实际是更新操作
    Result deleteRecord(Integer[] ids);

    IPage<RecordDto> queryRecordByParam();

    List<RecordDto> queryRecCountByDate(String beginTime, String endTime);

}
