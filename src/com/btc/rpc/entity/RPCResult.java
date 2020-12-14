package com.btc.rpc.entity;

/**
 *  自定义的rpc请求的响应
 */
public class RPCResult {
    private int code;//请求的状态码
    private String msg;//请求的描述信息
    private Result data;//请求的具体结果

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result getData() {
        return data;
    }

    public void setData(Result data) {
        this.data = data;
    }
}
