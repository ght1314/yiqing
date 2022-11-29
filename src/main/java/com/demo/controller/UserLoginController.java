package com.demo.controller;

import com.demo.dto.RetrievePwdDto;
import com.demo.dto.UserDto;
import com.demo.service.UserService;
import com.demo.utils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主要负责用户的密码找回和注册功能
 */
@Slf4j
@RestController
@RequestMapping("/User")
@Api(tags = "登录相关接口")
public class UserLoginController {
    @Autowired
    private UserService userService;

    //发送验证码
    @PostMapping("/sendSms")
    public Result sendSms(@RequestParam String account,
                          @RequestParam String userPhone) {
        return userService.sendSms(account, userPhone);
    }

    //验证验证码
    @PostMapping("/lookForPwdSubmit")
    public Result lookForPwd(RetrievePwdDto retrievePwdDto) {
        return userService.checkVesCode(retrievePwdDto);
    }

    //重置密码
    @PostMapping("/retrievePwdSubmit")
    public Result retrievePwdSubmit(RetrievePwdDto retrievePwdDto) {
        System.out.println(retrievePwdDto);
        return userService.updateUserPwd(retrievePwdDto.getAccount(), retrievePwdDto.getNewPwd());
    }

    //用户注册
    @PostMapping("/registerUser")
    public Result registerUser(UserDto userDto) {
        return userService.registerUser(userDto);
    }


}
