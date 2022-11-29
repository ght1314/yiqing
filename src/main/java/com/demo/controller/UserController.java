package com.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.dto.UserDto;
import com.demo.service.UserService;
import com.demo.utils.JsonObject;
import com.demo.utils.Result;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/Users")
@RequiresRoles(value = "admin", logical = Logical.OR)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/queryUserByParam")
    public JsonObject queryUserByParam(@RequestParam("page") Integer currentPage, @RequestParam("limit") Integer limit,
                                       @RequestParam(name = "params", required = false) String queryParams) {
        JsonObject<UserDto> object = new JsonObject<>();
        if (queryParams != null) {
            JSONObject jsonObject = JSON.parseObject(queryParams);
            String keyword = jsonObject.getString("keyword");
            IPage<UserDto> dtoIPage = userService.queryUserByParam(currentPage, limit, keyword);
            object.setCode(0);
            object.setMsg("ok");
            object.setCount(dtoIPage.getTotal());
            object.setData(dtoIPage);
            return object;
        } else {
            IPage<UserDto> dtoIPage = userService.queryUserByParam(currentPage, limit, queryParams);
            object.setCode(0);
            object.setMsg("ok");
            object.setCount(dtoIPage.getTotal());
            object.setData(dtoIPage);
            return object;
        }
    }

    @PostMapping("/addUser")
    public Result addAddress(UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PostMapping("/deleteUser")
    public Result deleteAddress(Integer[] ids) {
        return userService.deleteUser(ids);
    }

    @PostMapping("/updateUser")
    public Result updateUser(UserDto userDto) {
        return userService.updateUser(userDto);
    }


}

