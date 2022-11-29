package com.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_resident")
@ApiModel(value="ResidentEntity对象", description="")
public class ResidentEntity implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "res_id", type = IdType.AUTO)
    private Integer resId;

    private String resName;

    private Integer resSex;

    private String resPhone;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String  resIntotime;
    @ApiModelProperty("0表示正常，1表示低风险，2表示高风险")
    private Integer resStatus;

    private String resRemark;

    private String resPhoto;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;


}
