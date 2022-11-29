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
@TableName("tb_role_auth")
@ApiModel(value="RoleAuthEntity对象", description="")
public class RoleAuthEntity implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "role_auth", type = IdType.AUTO)
    private Integer roleAuth;

    private Integer roleId;

    private Integer authId;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
      @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
