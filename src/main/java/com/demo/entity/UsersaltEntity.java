package com.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_usersalt")
@ApiModel(value="UsersaltEntity对象", description="")
public class UsersaltEntity implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "salt_id", type = IdType.AUTO)
    private Integer saltId;

    private String saltText;

    @ApiModelProperty(value = "0未使用，1密码已经被使用，默认为0")
    private Integer saltStatus;

    @ApiModelProperty(value = "0代表生成的管理员的salt，1生成的为普通用户，默认为1")
    private Integer saltRole;

      @TableField(fill = FieldFill.INSERT)
    private Date createTime;

      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;


}
