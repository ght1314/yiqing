package com.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_authority")
@ApiModel(value="AuthorityEntity对象", description="")
public class AuthorityEntity implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "auth_id", type = IdType.AUTO)
    private Integer authId;

    @ApiModelProperty(value = "系统管理员等级1 普通管理员等级2")
    private String authName;

    private String authDescribe;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
