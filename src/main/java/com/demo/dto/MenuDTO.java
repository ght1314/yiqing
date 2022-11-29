package com.demo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

    @Data
    @EqualsAndHashCode(callSuper = false)
    @TableName("tb_menu")
    @ApiModel(value="MenuEntity对象", description="")
    public class MenuDTO implements Serializable {

        private static final long serialVersionUID=1L;

        @ApiModelProperty(value = "菜单ID")
        @TableId(value = "menu_id", type = IdType.AUTO)
        private Integer menuId;

        @ApiModelProperty(value = "菜单名称")
        private String title;

        @ApiModelProperty(value = "父菜单ID")
        private Integer parentId;

        @ApiModelProperty(value = "菜单路径")
        private String href;

        @ApiModelProperty(value = "图标")
        private String icon;

        private String target;


        private List<MenuDTO> child;

        @ApiModelProperty(value = "0表示未删除 1删除")
        @TableLogic
        private Integer deleted;


    }

