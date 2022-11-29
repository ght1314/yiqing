package com.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.mapper.AddressMapper;
import com.demo.mapper.RecordMapper;
import com.demo.mapper.ResidentMapper;
import com.demo.service.RecordService;
import com.demo.dto.RecordDto;
import com.demo.dto.ResidentDto;
import com.demo.dto.VistorDto;
import com.demo.entity.RecordEntity;
import com.demo.mapper.VisitorMapper;
import com.demo.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务实现类
 */
@Slf4j
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, RecordEntity> implements RecordService {

    @Resource
    private RecordMapper recordMapper;
    @Resource
    private ResidentMapper residentMapper;
    @Resource
    private VisitorMapper visitorMapper;
    @Resource
    private AddressMapper addressMapper;


    /**
     * 分页显示出入记录，并可根据关键字搜索
     *
     * @param currentPage
     * @param limit
     * @param keyword
     * @return
     */
    @Override
    public IPage<RecordDto> queryRecordByParam(Integer currentPage, Integer limit, String keyword) {
        Page<RecordDto> page = new Page<>(currentPage, limit);
        IPage<RecordDto> iPage = recordMapper.queryRecordByParam(page, keyword);
        //当前页中数据个数
        for (int i = 0; i < iPage.getRecords().size(); i++) {
            //获取登记者类型，0为业主  1 为访客
            if (iPage.getRecords().get(i).getRecPerType() == 0) {
                //业主
                ResidentDto resident = residentMapper.queryResidentById(iPage.getRecords().get(i).getRecPersonId());
                iPage.getRecords().get(i).setVisName(resident.getResName());
                iPage.getRecords().get(i).setVisPhone(resident.getResPhone());
                iPage.getRecords().get(i).setRecPersonId(resident.getResId());
            } else {
                VistorDto visitor = visitorMapper.queryVisitorById(iPage.getRecords().get(i).getRecPersonId());
                iPage.getRecords().get(i).setVisName(visitor.getVisName());
                iPage.getRecords().get(i).setVisPhone(visitor.getVisPhone());
                iPage.getRecords().get(i).setAddId(visitor.getAddId());
                iPage.getRecords().get(i).setRecPersonId(visitor.getVisId());
            }
        }
        return iPage;

    }

    /**
     * 增加一条记录，并通过体温对业主状态进行改变
     *
     * @param recordDto
     * @return
     */
    @Override
    public Result addRecord(RecordDto recordDto) {
        //设置记录初始值，count为设置的初值
        int count = recordMapper.queryRecCount().size() + 1001;
        recordDto.setRecId(Integer.toString(count));
        int personType = recordDto.getRecPerType();
        //业主登记
        if (personType == 0) {
            //查询业主编号是否存在
            if (residentMapper.queryResidentById(recordDto.getRecPersonId()) == null) {
                return Result.fail("用户编号不存在！");
            } else {
                recordMapper.addRecordRes(recordDto);
                //体温测定
                if (recordDto.getRecTempera() >= 37.30) {
                    //发烧37.3以上的标记为高风险用户
                    residentMapper.updateStatusByID(recordDto.getRecPersonId(), 2);
                    //该业主的统一地址业主标记为低风险
                    List<ResidentDto> dtoList = residentMapper.queryResByAddId(residentMapper.queryAddIdByResId(recordDto.getRecPersonId()));
                    for (int i = 0; i < dtoList.size(); i++) {
                        residentMapper.updateStatusByID(dtoList.get(i).getResId(), 1);
                    }
                }
                return Result.ok("添加记录成功");
            }

        } else //访客登记信息
        {
            //访问地址是否存在
            Integer addId = addressMapper.queryVisIdByparmas(recordDto.getAddHouse(), recordDto.getAddUnit());
            if (addId == null) {
                return Result.fail("受访地址不存在，请核对后重试~");
            } else {
                recordDto.setAddId(addId);
                recordMapper.addRecordVis(recordDto);
                //体温测定
                if (recordDto.getRecTempera() >= 37.30) {
                    //访客发烧37.3以上的地址 其中的业主都标记为低风险
                    List<ResidentDto> dtoList = residentMapper.queryResByAddId(addId);
                    for (int i = 0; i < dtoList.size(); i++) {
                        residentMapper.updateStatusByID(dtoList.get(i).getResId(), 1);
                    }
                }
                return Result.ok("添加记录" + recordDto.getVisName() + "成功");
            }


        }
    }

    @Override
    public RecordDto queryRecordById(String recId) {
        return recordMapper.queryRecordById(recId);
    }

    @Override
    public Result updateRecord(RecordDto recordDto) {
        //判断登记者类型，0为业主，1为访客
        if (recordDto.getRecPerType() == 0) {
            recordMapper.updateRecordRes(recordDto);
            return Result.ok("更新登记信息成功！");
        } else {
            recordMapper.updateRecordVis(recordDto);
            return Result.ok("更新登记信息成功！");
        }
    }

    @Override
    public Result deleteRecord(Integer[] ids) {
        //删除ID是否为空
        if (ids.length > 0) {
            //recordMapper.deleteRecord(ids);
            for (int i = 0; i < ids.length; i++) {
                System.out.print(ids[i]);
            }
            recordMapper.deleteRecord(ids);
            return Result.ok("删除记录成功！");
        } else {
            return Result.fail("删除用户ID为空，删除失败");
        }
    }

    @Override
    public IPage<RecordDto> queryRecordByParam() {
        Page<RecordDto> page = new Page<>();
        return recordMapper.queryRecordByParam(page, null);
    }

    @Override
    public List<RecordDto> queryRecCountByDate(String beginTime, String endTime) {
        return recordMapper.queryRecCountByDate(beginTime, endTime);
    }
}
