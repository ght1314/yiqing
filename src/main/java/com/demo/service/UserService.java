package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.dto.RetrievePwdDto;
import com.demo.dto.UserDto;
import com.demo.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.utils.Result;

/**
 *  服务类
 */
public interface UserService extends IService<UserEntity> {

     IPage<UserDto> queryUserByParam(Integer current,Integer limit,String keyword);

     Result  addUser(UserDto userDto);

     Result updateUser(UserDto userDto);

     Result deleteUser(Integer[] ids);

     UserDto queryUserDtoByAccount(String loginAccount);

     String updateUserPwd(String account,String oldPwd,String newPwd);

     Boolean updateUserInfo(UserDto userDto);

     Result sendSms(String account,String userPhone);
     //验证码验证
     Result checkVesCode(RetrievePwdDto retrievePwdDto);

     Result updateUserPwd(String account,String newPwd);

     Result registerUser(UserDto userDto);
}
