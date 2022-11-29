package com.demo.controller;

import com.demo.dto.UserDto;
import com.demo.realm.MyShiroRealm;
import com.demo.service.UserService;
import com.demo.utils.CookieUtil;
import com.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/User")
@Api(value = "用户登录模块，进行个人信息管理", tags = "用户登录相关信息接口")
public class LoginController {
    @Autowired
    private UserService userService;

    @Resource
    private MyShiroRealm shiroRealm;


    @RequestMapping("/main")
    public String mainPage(HttpServletRequest request, Model model) {
        String account = CookieUtil.getCookieValue(request, "account");
        UserDto user = userService.queryUserDtoByAccount(account);
        model.addAttribute("user", user);
        request.getSession().setAttribute("user", user);
        return "index";
    }

    @PostMapping("/doLogin")
    @ResponseBody
    public Result doLoginPage(@RequestParam String loginAccount, @RequestParam String password,
                              @RequestParam(defaultValue = "false") Boolean remeber) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(loginAccount);
        token.setPassword(password.toCharArray());
        //开启RememberMe
        token.setRememberMe(remeber);
        try {
            //进行登录的认证
            subject.login(token);
            //登录认证成功后，将shiro中保存的用户对象取出，放到session中
            String userAccount = (String) subject.getPrincipal();
            UserDto user = userService.queryUserDtoByAccount(userAccount);
            //将用户对象的ID放到session域中
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("userSession", user);
            return Result.ok("登录成功", user);
        } catch (UnknownAccountException e) {
            return Result.fail("账号不存在！");
        } catch (IncorrectCredentialsException e) {
            return Result.fail("密码错误！");
        } catch (ExcessiveAttemptsException e) {
            return Result.fail("登录失败！");
        }
    }

    //修改密码
    @PostMapping("/pwdSubmit")
    @ResponseBody
    public Result pwdSubmit(HttpServletRequest request, @RequestParam String oldPwd,
                            @RequestParam String newPwd) {
        String account = CookieUtil.getCookieValue(request, "account");
        UserDto user = userService.queryUserDtoByAccount(account);
        String updateUserPwd = userService.updateUserPwd(user.getAccount(), oldPwd, newPwd);
        if ("true".equals(updateUserPwd)) {
            //修改密码后需要将当前用户shiro缓存中的数据清空
            shiroRealm.clearCachedAuthenticationInfo(SecurityUtils.getSubject().getPrincipals());
            //清除授权信息
            shiroRealm.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
            return Result.ok(user.getUserName() + "密码修改成功");
        } else {
            return Result.fail(updateUserPwd);
        }
    }

    @PostMapping("/infoSubmit")
    @ResponseBody
    public Result infoSubmit(UserDto userDto) {
        Boolean info = userService.updateUserInfo(userDto);
        System.out.println(info);
        if (info) {
            return Result.ok();
        } else {
            return Result.fail("保存个人信息失败！");
        }

    }

    //    用户注销
    @ApiOperation("用户注销方法")
    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:login";
    }
}
