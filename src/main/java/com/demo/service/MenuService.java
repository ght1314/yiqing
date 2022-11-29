package com.demo.service;

import com.demo.dto.MenuDTO;
import com.demo.entity.MenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  服务类
 */
public interface MenuService extends IService<MenuEntity> {

    List<MenuDTO> listByUserId(Integer userId);

}
