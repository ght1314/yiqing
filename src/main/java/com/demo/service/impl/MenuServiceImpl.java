package com.demo.service.impl;

import com.demo.dto.MenuDTO;
import com.demo.entity.MenuEntity;
import com.demo.mapper.MenuMapper;
import com.demo.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *  服务实现类
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Cacheable(cacheNames = {"menus"})
    @Override
    public List<MenuDTO> listByUserId(Integer userId) {
        List<MenuDTO> list = menuMapper.listByUserId(userId);
        list = sortTree(list,-1);
        return list;
    }

    /**
     * 递归查询当前菜单的所有子菜单，并将所有的子菜单放到当前菜单的List集合中
     * @param list
     * @param parentId
     * @return
     */
    private List<MenuDTO> sortTree(List<MenuDTO> list, int parentId){
        ArrayList<MenuDTO> returnList = new ArrayList<>();
        for (MenuDTO dto : list) {
            if (parentId == dto.getParentId()){
                dto.setChild(sortTree(list,dto.getMenuId()));
                returnList.add(dto);
            }
        }
        return returnList;
    }
}
