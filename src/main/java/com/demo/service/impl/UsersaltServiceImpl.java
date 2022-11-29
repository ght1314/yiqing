package com.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.service.UsersaltService;
import com.demo.dto.UsersaltDto;
import com.demo.mapper.UsersaltMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.utils.Result;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 *  服务实现类
 */
@Service
public class UsersaltServiceImpl extends ServiceImpl<UsersaltMapper, UsersaltDto> implements UsersaltService {

    @Autowired
    private UsersaltMapper usersaltMapper;


    @Override
    public Result addSalt(int count,int role) {
        //管理员为1
        if (role == 1){
            for (int i = 0; i < count; i++) {
                UsersaltDto usersaltDto = new UsersaltDto();
                usersaltDto.setSaltText(RandomStringUtils.randomAlphanumeric(20));
                usersaltDto.setSaltRole(1);
                usersaltMapper.insert(usersaltDto);
            }
            return Result.ok("添加管理员注册码成功，共计："+count+"条");
        }else {//用户为2
            for (int i = 0; i < count; i++) {
                UsersaltDto usersaltDto = new UsersaltDto();
                usersaltDto.setSaltRole(2);
                usersaltDto.setSaltText(RandomStringUtils.randomAlphanumeric(20));
                usersaltMapper.insert(usersaltDto);
            }
            return Result.ok("添加用户注册码成功，共计："+count+"条");
        }

    }

    @Override
    public IPage<UsersaltDto> querySaltByParam(Integer currentPage, Integer limit,String keyword) {
        Page<UsersaltDto> page = new Page<>(currentPage,limit);
        if (keyword == null){
            return usersaltMapper.selectPage(page,null);
        }else {
            QueryWrapper<UsersaltDto> wrapper = new QueryWrapper<>();
            //根据状态进行查询
            wrapper.eq("salt_status",keyword);
            return usersaltMapper.selectPage(page,wrapper);
        }
    }


    //删除注册码
    @Override
    public Result deleteSalt(Integer[] saltId) {
        if (saltId.length > 0){
            usersaltMapper.deleteBatchIds(Arrays.asList(saltId));
            return Result.ok("删除注册码成功");
        }else {
            return Result.fail("请选择至少一条删除的数据");
        }
    }



}
