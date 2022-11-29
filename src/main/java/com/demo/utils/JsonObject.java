package com.demo.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * json返回对象 与前台json格式对应
 */
@Data
public class JsonObject<T> {

    private Integer code;
    private String msg;
    private Long count;
    private IPage<T> data;
//    private HashMap<Object,Object> page;
}
