package com.demo.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_vercode")
@ApiModel(value="VercodeEntity对象", description="")
public class VercodeDto implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "ver_id", type = IdType.AUTO)
    private Integer verId;

    private String account;

    private String verCode;

      @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableLogic
    private Integer deleted;


}
