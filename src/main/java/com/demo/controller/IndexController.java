package com.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/User")
@Api(value = "页面跳转", tags = "页面跳转相关接口")
public class IndexController {
    @RequestMapping("/login")
    public String loginPage() {
        return "page/sys/login";
    }

    @RequestMapping("/welcome")
    public String welcomePage() {
        return "page/sys/welcome";
    }

    @RequestMapping("/lookForPwd")
    public String lookForPwdPage() {
        return "page/sys/lookForPwd";
    }

    @RequestMapping("/register")
    public String registerPage() {
        return "page/sys/register";
    }

    @RequestMapping("/userInfo")
    public String setUserPage() {
        return "page/user/user-setting";
    }

    @RequestMapping("/updatePwd")
    public String updatePwdPage() {
        return "page/user/user-password";
    }

    @RequestMapping("/resident")
    public String residentPage() {
        return "page/residents/resident";
    }

    @RequestMapping("/addResident")
    public String addResidentPage() {
        return "page/residents/addResident";
    }

    @RequestMapping("/perilPage")
    public String perilPage() {
        return "page/residents/peril";
    }

    @RequestMapping("/resCount")
    public String resCountPage() {
        return "page/residents/resCount";
    }

    @RequestMapping("/records")
    public String recordsPage() {
        return "page/record/records";
    }

    @RequestMapping("/resRecord")
    public String recordPage() {
        return "page/record/resRecord";
    }

    @RequestMapping("/visRecord")
    public String visRecordPage() {
        return "page/record/visRecord";
    }

    @RequestMapping("/vistors")
    public String vistorsPage() {
        return "page/vistor/vistors";
    }

    @RequestMapping("/vistor")
    public String vistorPage() {
        return "page/vistor/vistor";
    }

    @RequestMapping("/address")
    public String addressPage() {
        return "page/address/address";
    }

    @RequestMapping("/addAddress")
    public String addAddressPage() {
        return "page/address/addAddress";
    }

    @RequestMapping("/users")
    public String userPage() {
        return "page/user/users";
    }

    @RequestMapping("/addUser")
    public String addUserPage() {
        return "page/user/addUser";
    }

    @RequestMapping("/feedBack")
    public String feedbackPage() {
        return "page/info/feedBack";
    }

    @RequestMapping("/userSalt")
    public String userSaltPage() {
        return "page/salt/userSalt";
    }

    @RequestMapping("/404")
    public String exceptionPage() {
        return "page/sys/404";
    }

}
