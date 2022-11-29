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

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_record")
@ApiModel(value="RecordEntity对象", description="")
public class RecordEntity implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "rec_id", type = IdType.AUTO)
    private String recId;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date recTime;

    @ApiModelProperty(value = "进入是1 出去是0")
    private Integer recType;

    private Integer recPersonId;
    @ApiModelProperty(value = "业主是0 访客是0")
    private Integer recperType;

    private String recOrderadd;

    private Float recTempera;

    private String recRemark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;


}
