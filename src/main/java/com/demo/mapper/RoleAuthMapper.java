package com.demo.mapper;

import com.demo.dto.RoleAuthDto;
import com.demo.entity.RoleAuthEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 *  Mapper 接口
 */
public interface RoleAuthMapper extends BaseMapper<RoleAuthEntity> {

    List<RoleAuthDto> getAuthByRole(int roleId);
}
