package com.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.demo.dto.MenuDTO;
import com.demo.dto.UserDto;
import com.demo.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "获取菜单", tags = "获取菜单信息接口")
@RestController
@RequestMapping("/menus")
@Slf4j
public class MenuController {

    @Resource
    private MenuService menuService;

    @ApiOperation(value = "通过登录的用户ID获取菜单信息", notes = "通过登录的用户ID获取菜单信息")
    @GetMapping("")
    public String listMenus() {
        //获取当前用户的菜单列表
        UserDto userSession = (UserDto) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        JSONObject jsonObject = new JSONObject();
        List<MenuDTO> list = menuService.listByUserId(userSession.getUserId());
        return JSONObject.toJSONString(list);
    }

}

