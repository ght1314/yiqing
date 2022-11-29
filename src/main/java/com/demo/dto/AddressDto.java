package com.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDto implements Serializable {

    private Integer addId;

    @ApiModelProperty(value = "地址代号")
    private String addSort;

    @ApiModelProperty(value = "小区名称")
    private String addName;

    private Integer addHouse;

    private Integer addUnit;

    @ApiModelProperty(value = "地址中业主数量")
    private Integer addCount;

    //业主相关信息

    private Integer resId;

    private String resName;

    private String resPhone;


}
