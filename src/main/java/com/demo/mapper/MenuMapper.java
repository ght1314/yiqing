package com.demo.mapper;

import com.demo.entity.MenuEntity;
import com.demo.dto.MenuDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 *  Mapper 接口
 */
public interface MenuMapper extends BaseMapper<MenuEntity> {

    List<MenuDTO> listByUserId(Integer userId);

}
