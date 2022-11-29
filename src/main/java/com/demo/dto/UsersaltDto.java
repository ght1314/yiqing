package com.demo.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_usersalt")
@ApiModel(value="UsersaltDto对象", description="")
public class UsersaltDto implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "salt_id", type = IdType.AUTO)
    private Integer saltId;

    private String saltText;

    @ApiModelProperty(value = "0未使用，1密码已经被使用，默认为0")
    private Integer saltStatus;

    @ApiModelProperty(value = "0代表生成的管理员的salt，1生成的为普通用户，默认为1")
    private Integer saltRole;

    @ApiModelProperty(value = "添加的注册码的数量")
    private transient   Integer count;

    @ApiModelProperty(value = "代表注册码可注册的角色")
    private transient   Integer role;

      @TableField(fill = FieldFill.INSERT)
    private   Date createTime;

      @TableField(fill = FieldFill.INSERT_UPDATE)
    private   Date updateTime;

    @TableLogic
    private   Integer deleted;


}
