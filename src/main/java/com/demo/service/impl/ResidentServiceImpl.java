package com.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dto.ResidentDto;
import com.demo.entity.ResidentEntity;
import com.demo.mapper.AddressMapper;
import com.demo.mapper.ResidentMapper;
import com.demo.service.ResidentService;
import com.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 服务实现类
 */
@Service
public class ResidentServiceImpl extends ServiceImpl<ResidentMapper, ResidentEntity> implements ResidentService {
    @Autowired
    private ResidentMapper residentMapper;
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 业主的查询
     *
     * @param currentPage
     * @param limit
     * @param keyword
     * @param house
     * @param unit
     * @return
     */
    @Cacheable(cacheNames = {"residentsByParams"})
    @Override
    public IPage<ResidentDto> residentsByParams(Integer currentPage, Integer limit, String keyword, String house, String unit) {
        Page<ResidentDto> page = new Page<>(currentPage, limit);
        return residentMapper.residentsByParams(page, keyword, house, unit);
    }

    /**
     * 新增业主
     *
     * @param resident
     * @return
     */
    @CacheEvict(cacheNames = {"residentsByParams"})
    @Override
    public Result addResident(ResidentDto resident) {
        //判断添加的用户编号是否存在
        if (residentMapper.queryResidentById(resident.getResId()) == null) {
            //获得增加用户的地址号,
            Integer addId = addressMapper.queryVisIdByparmas(resident.getAddHouse(), resident.getAddUnit());
            if (addId == null) { //新业主，新地址
                resident.setAddId(addId);
                System.out.println("新业主新地址" + resident);
                residentMapper.addResident(resident);
                return Result.ok("添加用户" + resident.getResName() + "成功！");
            } else { // 地址已存在,增加业主，地址业主数量+1
                Integer addCount = addressMapper.queryAddressById(addId).getAddCount();
                resident.setAddCount(addCount + 1);
                resident.setAddId(addId);
                residentMapper.addResidentOld(resident);
                return Result.ok("添加用户" + resident.getResName() + "成功！");
            }
        } else {
            return Result.fail("添加的用户编号已存在！");
        }
    }

    /**
     * 批量添加数据
     *
     * @param residentDtos
     * @return
     */
    @Override
    public int insertBatchResident(List<ResidentDto> residentDtos) {
        int batchResident = residentMapper.insertBatchResident(residentDtos);
        return batchResident;
    }

    /**
     * 通过查询用户信息
     *
     * @param resId
     * @return
     */
    @Override
    public ResidentDto queryResidentById(int resId) {
        return residentMapper.queryResidentById(resId);
    }

    /**
     * 业主信息更新
     *
     * @param residentDto
     * @return
     */
    @CacheEvict(cacheNames = {"residentsByParams"})
    @Override
    public Result updateResident(ResidentDto residentDto) {
        residentMapper.updateResident(residentDto);
        return Result.ok("更新业主" + residentDto.getResName() + "信息成功");
    }

    /**
     * 业主删除
     *
     * @param ids
     * @return
     */
    @CacheEvict(cacheNames = {"residentsByParams"})
    @Override
    public Result deleteResidents(Integer[] ids) {
        if (ids.length < 1) {
            return Result.fail("删除数量不能小于1");
        } else {
            //删除业主时，业主对应地址的业主数量-1
            for (int i = 0; i < ids.length; i++) {
                //根据id查找业主的地址ID
                ResidentDto residentDto = residentMapper.queryResidentById(ids[i]);
                residentDto.setAddCount(residentDto.getAddCount() - 1);
                residentMapper.deleteResidents(ids[i]);
            }
            return Result.ok();
        }
    }

    @Override
    public Result queryResCountl() {
        Page<ResidentDto> page = new Page<>();
        IPage<ResidentDto> iPage = residentMapper.residentsByParams(page, null, null, null);
        return Result.ok(iPage.getTotal());
    }

    @Override
    public IPage<ResidentDto> queryResPeril(Integer currentPage, Integer limit) {
        Page<ResidentDto> page = new Page<>(currentPage, limit);
        return residentMapper.queryResPeril(page);
    }

    @Override
    public Result queryResCountByDate() {
        String beginTime = null;
        String endTime = null;
        int size = 0;
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, HashMap<String, Object>> map = new HashMap<>();
        HashMap<String, Object> dataMap = new HashMap<>();
        HashMap<String, Object> dateMap = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();
        calendar.add(Calendar.DATE, -6);
        beginTime = dateFormat.format(calendar.getTime());
        calendar.add(Calendar.DATE, 1);
        endTime = dateFormat.format(calendar.getTime());
        size = residentMapper.queryResCountByDate(beginTime, endTime).size();
        dateList.add(beginTime);
        list.add(size);
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.DATE, 0);
            beginTime = dateFormat.format(calendar.getTime());
            calendar.add(Calendar.DATE, 1);
            endTime = dateFormat.format(calendar.getTime());
            size = residentMapper.queryResCountByDate(beginTime, endTime).size();
            dateList.add(beginTime);
            list.add(size);
        }
        dateMap.put("data", dateList);
        dateMap.put("type", "category");
        dateMap.put("boundaryGap", false);
        dataMap.put("name", "新增业主数");
        dataMap.put("type", "line");
        dataMap.put("data", list);
        map.put("date", dateMap);
        map.put("data", dataMap);

        return Result.ok(map);
    }
}
