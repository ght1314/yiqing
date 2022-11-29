package com.demo.service;

import com.demo.dto.RoleAuthDto;
import com.demo.entity.RoleAuthEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  服务类
 */
public interface RoleAuthService extends IService<RoleAuthEntity> {

    List<RoleAuthDto> getAuthByRole(int roleId);
}
