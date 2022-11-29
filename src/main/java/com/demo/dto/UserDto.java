package com.demo.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer userId;
    private String account;
    private String password;
    private String userName;
    private String userPhone;

//    注册码
    private String userSalt;

    //权限相关
    private Integer roleId;
    private String roleName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
