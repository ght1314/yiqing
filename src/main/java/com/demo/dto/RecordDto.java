package com.demo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 业主记录登记dto
 */
@Data
public class RecordDto implements Serializable {
    private static final long serialVersionUID=1L;

    @TableId(value = "rec_id", type = IdType.AUTO)
    private String recId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String recTime;

    @ApiModelProperty(value = "进入是1 出去是0")
    private Integer recType;
    private Integer recPersonId;
    private Integer recPerType;
    private String recOrderadd;


    private Float recTempera;

    private String recRemark;
    //携带的地址信息 进行验证
    private String addName;

    private Integer addHouse;

    private Integer addUnit;

    //业主或访客信息字段

    private Integer visId;

    private Integer addId;

    private String visName;

    private String visPhone;


}
