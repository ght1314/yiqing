package com.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.dto.RetrievePwdDto;
import com.demo.dto.UserDto;
import com.demo.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Mapper 接口
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    IPage<UserDto> queryUserByParam(@Param("page") Page<UserDto> page,
                                       @Param("keyword")String keyword);

    @Transactional(propagation = Propagation.REQUIRED)
    void  addUser(UserDto userDto);

    UserDto queryUserDtoByAccount(String loginAccount);

    @Transactional(propagation = Propagation.REQUIRED)
    void updateUser(UserDto userDto);

    @Transactional(propagation = Propagation.REQUIRED)
    void deleteUser(Integer[] ids);


    @Transactional(propagation = Propagation.REQUIRED)
    Boolean updateUserPwd(String account,String newPwd);

    @Transactional(propagation = Propagation.REQUIRED)
    Boolean updateUserInfo(UserDto userDto);

    String getPwdByAccount(String account);

    //根据账号查找用户 找回密码
    RetrievePwdDto queryUserByAccount(String account);
}
