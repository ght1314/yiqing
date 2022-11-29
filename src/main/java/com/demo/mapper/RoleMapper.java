package com.demo.mapper;

import com.demo.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *  Mapper 接口
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {
    /**
     * 通过用户账号 获取用户角色
     * @param loginAccount
     * @return
     */
    String findRoleByAccount(String loginAccount);

    /**
     * 通过角色名查询角色ID
     */
    Integer findRoleIDByName(String roleName);

}
