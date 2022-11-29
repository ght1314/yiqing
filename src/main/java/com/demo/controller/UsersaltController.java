package com.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.dto.UsersaltDto;
import com.demo.service.UsersaltService;
import com.demo.utils.JsonObject;
import com.demo.utils.Result;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 注册码相关
 */
@RestController
@RequestMapping("/Usalt")
@RequiresRoles(value = "admin" , logical = Logical.OR)
public class UsersaltController {
    //添加注册码
    @Autowired
    private UsersaltService usersaltService;

    @GetMapping("/querySaltByParam")
    public JsonObject queryUserByParam(@RequestParam("page")Integer currentPage, @RequestParam("limit")Integer limit)
    {
             JsonObject<UsersaltDto> object = new JsonObject<>();
            IPage<UsersaltDto> dtoIPage = usersaltService.querySaltByParam(currentPage,limit,null);
            object.setCode(0);
            object.setMsg("ok");
            object.setCount(dtoIPage.getTotal());
            object.setData(dtoIPage);
            return object;

    }

    @PostMapping("/addSalt")
    public Result addSalt(Integer count, Integer role)
    {
        return usersaltService.addSalt(count,role);
    }

    @PostMapping("/deleteSalt")
    public Result deleteSalt(Integer[] ids)
    {
        System.out.println(ids);
        return usersaltService.deleteSalt(ids);
    }

    @PostMapping("/updateSalt")
    public Result updateSalt(UsersaltDto usersaltDto)
    {
        return Result.ok();
    }








}

