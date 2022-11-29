package com.demo.service.impl;

import com.demo.mapper.RoleAuthMapper;
import com.demo.dto.RoleAuthDto;
import com.demo.entity.RoleAuthEntity;
import com.demo.service.RoleAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 */
@Service
public class RoleAuthServiceImpl extends ServiceImpl<RoleAuthMapper, RoleAuthEntity> implements RoleAuthService {

    @Autowired
    private RoleAuthMapper roleAuthMapper;
    @Override
    public List<RoleAuthDto> getAuthByRole(int roleId) {
        List<RoleAuthDto> authByRole = roleAuthMapper.getAuthByRole(roleId);
        return authByRole;
    }
}
