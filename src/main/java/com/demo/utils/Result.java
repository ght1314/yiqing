package com.demo.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回前端数据封装类
 */
@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;
    private Long count;
    private Object data;


    public Result() {
    }

    private Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //    仅返回处理结果，不携带数据
    public static Result ok() {
        return new Result(Constants.OK_CODE, Constants.OK_MSG, null);
    }
    //    仅返回消息内容
    public static Result ok(String msg) {
        return new Result(Constants.OK_CODE,msg, null);
    }
    //     仅返回数据信息
    public static Result ok(Object data) {
        return new Result(Constants.OK_CODE, Constants.OK_MSG, data);
    }



    //返回消息内容和数据信息
    public static Result ok(String msg,Object data) {
        return new Result(Constants.OK_CODE, msg, data);
    }

    //    仅返回处理结果，不携带数据
    public static Result fail() {
        return new Result(Constants.FAIL_CODE, Constants.FAIL_MSG, null);
    }

    //返回消息内容
    public static Result fail(String msg) {
        return new Result(Constants.FAIL_CODE, msg, null);
    }

}


