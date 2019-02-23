package com.debug.mooc.dubbo.one.api.enums;/**
 * Created by Administrator on 2019/1/12.
 */

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/1/12 14:01
 **/
public enum  StatusCode {

    Success(0,"成功"),
    Fail(-1,"失败"),
    InvalidParams(200,"无效的参数"),
    ItemNotExist(201,"商品不存在!");

    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}





















