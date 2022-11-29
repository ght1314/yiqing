package com.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mapper.AddressMapper;
import com.demo.service.VisitorService;
import com.demo.dto.VistorDto;
import com.demo.entity.VisitorEntity;
import com.demo.mapper.VisitorMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 来访客人登记表 服务实现类
 */
@Service
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, VisitorEntity> implements VisitorService {

    @Autowired
    private VisitorMapper visitorMapper;
    @Autowired
    private AddressMapper addressMapper;


    @Override
    public IPage<VistorDto> queryVistorByParam(Integer currentPage, Integer limit, String keyword) {
        Page<VistorDto> page = new Page<>();
        return visitorMapper.queryvisitorByParam(page,keyword);
    }

    @Override
    public Result addVistor(VistorDto vistorDto) {
        if (addressMapper.queryVisIdByparmas(vistorDto.getAddHouse(),vistorDto.getAddUnit()) == null)
        {
            return Result.fail("添加的访客地址不存在！！！");
        }else {
            vistorDto.setAddId(addressMapper.queryVisIdByparmas(vistorDto.getAddHouse(),vistorDto.getAddUnit()));
            visitorMapper.addVistor(vistorDto);
            return Result.ok("增加访客"+vistorDto.getVisName()+"成功");
        }
    }

    @Override
    public Result deleteVistors(Integer[] ids) {
        if (ids.length > 0 ){
            visitorMapper.deleteVistor(ids);
            return Result.ok("删除游客成功！");
        }else {
            return Result.fail("所选用户编号为空，删除失败！！！");
        }


    }

    @Override
    public Result updateVistor(VistorDto vistorDto) {
        if (addressMapper.queryVisIdByparmas(vistorDto.getAddHouse(),vistorDto.getAddUnit()) == null)
        {
            return Result.fail("更新的访客地址不存在！！！");
        }else {
            vistorDto.setAddId(addressMapper.queryVisIdByparmas(vistorDto.getAddHouse(),vistorDto.getAddUnit()));
            visitorMapper.updateVistor(vistorDto);
            return Result.ok("更新游客"+vistorDto.getVisName()+"成功");
        }
    }

    @Override
    public IPage<VistorDto> queryVistorByParam() {
        Page<VistorDto> page = new Page<>();
        return visitorMapper.queryvisitorByParam(page,null);
    }
}
