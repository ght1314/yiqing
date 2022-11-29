package com.demo.service.impl;

import com.demo.mapper.RoleMapper;
import com.demo.service.RoleService;
import com.demo.entity.RoleEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *  服务实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public String findRoleByAccount(String loginAccount) {
        String role = roleMapper.findRoleByAccount(loginAccount);
        return role;
    }
}
