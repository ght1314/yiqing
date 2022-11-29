package com.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mapper.RoleMapper;
import com.demo.mapper.VercodeMapper;
import com.demo.dto.RetrievePwdDto;
import com.demo.dto.UserDto;
import com.demo.dto.UsersaltDto;
import com.demo.dto.VercodeDto;
import com.demo.entity.UserEntity;
import com.demo.mapper.UserMapper;
import com.demo.mapper.UsersaltMapper;
import com.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.utils.MD5Util;
import com.demo.utils.Result;
import com.demo.utils.sendSms;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;

/**
 *  服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private sendSms sendSms;
    @Autowired
    private VercodeMapper vercodeMapper;

    @Autowired
    private UsersaltMapper usersaltMapper;



    /**
     * 获取用户数据信息
     * @param current
     * @param limit
     * @param keyword
     * @return
     */
    @Override
    public IPage<UserDto> queryUserByParam(Integer current, Integer limit, String keyword) {
        Page<UserDto> page = new Page<>(current, limit);
        return  userMapper.queryUserByParam(page, keyword);
    }

    @Override
    public Result addUser(UserDto userDto) {
        if (userDto == null){
            return  Result.fail("添加的用户为空，请重试");
        }else {
            if (userMapper.queryUserDtoByAccount(userDto.getAccount()) == null ){
                //添加用户角色
                userDto.setRoleId(roleMapper.findRoleIDByName(userDto.getRoleName()));
                //密码加密
                userDto.setPassword(MD5Util.setSalt(userDto.getAccount(),userDto.getPassword()));
                userMapper.addUser(userDto);
                return Result.ok("添加用户"+userDto.getUserName()+"成功！");
            }else {
                return  Result.fail("添加的用户账号已存在，请更换用户账号");
            }

        }
    }

    @Override
    public Result updateUser(UserDto userDto) {
        if (userDto != null){
            userMapper.updateUser(userDto);
            return Result.ok("修改用户"+userDto.getUserName()+"成功！");
        }else {
            return Result.fail("修改用户信息失败！！！");
        }

    }

    @Override
    public Result deleteUser(Integer[] ids) {
        if (ids.length > 0 ){
            userMapper.deleteUser(ids);
            return Result.ok();
        }else {
            return Result.fail("删除的用户不能为空");
        }
    }

    @Override
    public UserDto queryUserDtoByAccount(String loginAccount) {
        return userMapper.queryUserDtoByAccount(loginAccount);
    }



    @Override
    public String updateUserPwd(String account, String oldPwd, String newPwd) {
        //判断旧密码是否和用户登录密码一致
        String simpleHash = MD5Util.setSalt(account, oldPwd);
        String pwd = userMapper.getPwdByAccount(account);
        if (pwd.equals(simpleHash))
        {
            String pwdSalt = MD5Util.setSalt(account, newPwd);
            return String.valueOf(userMapper.updateUserPwd(account, pwdSalt));

        }else{
            return "旧密码输入错误";
        }


    }


    @Override
    public Boolean updateUserInfo(UserDto userDto) {
        return userMapper.updateUserInfo(userDto);
    }

    @Override
    public Result sendSms(String account, String userPhone) {
        //验证 用户名和手机号是否匹配
        if (userMapper.queryUserByAccount(account).getUserPhone().equals(userPhone) ){
            String verCode = RandomStringUtils.randomNumeric(4);

            HashMap<String, Object> map = new HashMap<>();
            map.put("code",verCode);
            System.out.println(verCode);
            //将生产的验证码存放到数据库中
            VercodeDto vercodeDto = new VercodeDto();
            vercodeDto.setAccount(account);
            vercodeDto.setVerCode(verCode);
            vercodeMapper.insert(vercodeDto);
            return Result.ok("发送验证码成功！");
        }else {
            return  Result.fail("用户名和手机号不匹配~请核对后重新输入");
        }
    }
    //验证码是否无误

    @Override
    public Result checkVesCode(RetrievePwdDto retrievePwdDto) {
        System.out.println(retrievePwdDto);
        QueryWrapper<VercodeDto> wrapper = new QueryWrapper<>();
        wrapper.eq("account",retrievePwdDto.getAccount());
        try {
            VercodeDto dto = vercodeMapper.selectOne(wrapper);
            Date createTime = dto.getCreateTime();
            Date date = new Date();
            long time = date.getTime() - createTime.getTime();
            //        验证码未被使用且没有超时(5分钟)
            if (time > 300){
                String verCode = dto.getVerCode();
                if (retrievePwdDto.getVerCode().equals(verCode))
                {
                    vercodeMapper.deleteById(dto.getVerId());
                    return  Result.ok(retrievePwdDto.getAccount());
                }else {
                    return Result.fail("验证码错误，请重新输入");
                }
            }else {
                vercodeMapper.deleteById(dto.getVerId());
                return Result.fail("验证码超时，请重新输入");
            }
        }catch (NullPointerException e){
            return Result.fail("验证码错误，请重试");
        }


    }
    //更新密码

    @Override
    public Result updateUserPwd(String account, String newPwd) {
        String pwdSalt = MD5Util.setSalt(account, newPwd);
        Boolean pwd = userMapper.updateUserPwd(account, pwdSalt);
        if (pwd){
            return Result.ok("找回密码成功，请使用新密码登录");
        }else {
            return Result.fail("找回密码失败，请重试");
        }
    }

    //用户注册

    @Override
    public Result registerUser(UserDto userDto) {
        //验证提交的用户注册验证码
        QueryWrapper<UsersaltDto> wrapper = new QueryWrapper<>();
        //验证注册码是否匹配
        wrapper.eq("salt_text",userDto.getUserSalt());
        UsersaltDto salt = usersaltMapper.selectOne(wrapper);
        //注册码错误
        if (salt == null){
            return  Result.fail("注册码不正确，请重试");
        } else if (salt.getSaltStatus() == 1){
            //注册码已被使用
           return Result.fail("注册码已被使用，请重试");
        }else if (!salt.getSaltRole().equals(userDto.getRoleId())){
            //注册码与用户申请角色不匹配
            return Result.fail("注册码与申请角色不匹配，请重试");
        }
        //实现用户注册

        //密码加密
        userDto.setPassword(MD5Util.setSalt(userDto.getAccount(),userDto.getPassword()));
        userMapper.addUser(userDto);
        //注册码状态置为1(已使用)
        salt.setSaltStatus(1);
        usersaltMapper.updateById(salt);
        //注册码被使用后 及时进行删除
        QueryWrapper<UsersaltDto> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq("salt_text",userDto.getUserSalt());
        usersaltMapper.delete(deleteWrapper);
        return Result.ok("用户"+userDto.getAccount()+"注册成功！");
    }


}
