package com.demo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("tb_role")
@ApiModel(value = "业主信息，地址信息整合", description = "")
public class ResidentDto implements Serializable {
    @ExcelProperty(index = 0, value = "业主编号")
    @TableId(value = "res_id", type = IdType.AUTO)
    private Integer resId;
    @ExcelProperty(index = 1, value = "业主名")
    private String resName;
    @ExcelProperty(index = 2, value = "性别")
    private Integer resSex;
    @ExcelProperty(index = 3, value = "手机号")
    private String resPhone;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(index = 4, value = "入住小区时间")
    private String resIntotime;
    @ExcelProperty(index = 5, value = "状态")
    @ApiModelProperty("0表示正常，1表示低风险，2表示高风险")
    private Integer resStatus;
    @ExcelProperty(index = 6, value = "备注")
    private String resRemark;
   
    private String resPhoto;
    //地址相关字段

    private Integer addId;

    private String addSort;

    private String addName;

    private Integer addHouse;

    private Integer addUnit;

    private Integer addCount;

}
