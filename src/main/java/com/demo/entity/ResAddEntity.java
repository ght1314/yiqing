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

/**
 * 地址业主关联表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_res_add")
@ApiModel(value="ResAddEntity对象", description="地址业主关联表")
public class ResAddEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "地址业主关联表")
      @TableId(value = "skip_id", type = IdType.AUTO)
    private Long skipId;

    @ApiModelProperty(value = "地址ID")
    private Integer addId;

    @ApiModelProperty(value = "业主ID")
    private Integer resId;

      @TableField(fill = FieldFill.INSERT)
    private Date createTime;

      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;


}
