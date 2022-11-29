package com.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_address")
@ApiModel(value = "AddressEntity对象", description = "")
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "add_id", type = IdType.AUTO)
    private Integer addId;

    @ApiModelProperty(value = "地址代号")
    private String addSort;

    @ApiModelProperty(value = "小区名称")
    private String addName;

    private Integer addHouse;

    private Integer addUnit;

    @ApiModelProperty(value = "地址中业主数量")
    private Integer addCount;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;


}
