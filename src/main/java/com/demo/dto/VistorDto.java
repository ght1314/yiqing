package com.demo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class VistorDto implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "vis_id", type = IdType.AUTO)
    private Integer visId;

    private Integer addId;

    private String visName;

    private String visPhone;

    private String visRemark;

    //小区编号  地址信息

    private String addName;

    private Integer addNumber;

    private Integer addHouse;

    private Integer addUnit;
}
