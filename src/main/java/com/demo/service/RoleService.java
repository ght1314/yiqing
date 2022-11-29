package com.demo.service;

import com.demo.entity.RoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  服务类
 */
public interface RoleService extends IService<RoleEntity> {

    String findRoleByAccount(String loginAccount);

}
