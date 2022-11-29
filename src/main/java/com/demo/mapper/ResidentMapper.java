package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.dto.RecordDto;
import com.demo.dto.ResidentDto;
import com.demo.entity.ResidentEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Mapper 接口
 */
public interface ResidentMapper extends BaseMapper<ResidentEntity> {

    IPage<ResidentDto> residentsByParams(@Param("page") Page<ResidentDto> page, @Param("keyword") String keyword,
                                         @Param("house") String house, @Param("unit") String unit);


    //添加新用户，新地址
    @Transactional
    void addResident(ResidentDto resident);

    //添加新用户，旧地址
    @Transactional
    void addResidentOld(ResidentDto resident);

    //根据ID查询业主信息
    ResidentDto queryResidentById(int resId);

    //根据ID修改状态
    void updateStatusByID(@Param("resId") Integer resId,
                          @Param("Status") Integer Status);

    /**
     * 查询地址ID的所有业主信息
     *
     * @param addId
     * @return
     */
    List<ResidentDto> queryResByAddId(Integer addId);

    /**
     * 通过业主编号查找业主地址ID
     *
     * @param resId
     * @return
     */
    Integer queryAddIdByResId(Integer resId);

    //    修改操作
    @Transactional
    void updateResident(ResidentDto residentDto);

    //    删除数据通过ResID（一条或多条）
    void deleteResidents(Integer id);

    /**
     * 高危业主查询
     */
    IPage<ResidentDto> queryResPeril(@Param("page") Page<ResidentDto> page);

    List<RecordDto> queryResCountByDate(@Param("beginTime") String beginTime,
                                        @Param("endTime") String endTime);

    int insertBatchResident(@Param("residentDtos") List<ResidentDto> residentDtos);
}
