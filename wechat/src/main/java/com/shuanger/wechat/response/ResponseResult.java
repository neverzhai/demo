package com.shuanger.wechat.response;

import lombok.Data;


@Data
public class ResponseResult<T> {

    private String code;

    private String message;

    private T data;

    public static <T> ResponseResult createSuccess(T data) {
        return ResponseResult.create(BusinessCode.SUCCESS, data);
    }

    public static ResponseResult create(BusinessCode code, Object data) {
        return new ResponseResult(code.getCode(), code.getMsg(), data);
    }

    public static ResponseResult create(BusinessCode code) {
        return new ResponseResult(code.getCode(), code.getMsg(), null);
    }

    public static ResponseResult create(String code, String message) {
        return new ResponseResult(code, message, null);
    }

    private ResponseResult(String code, String resultMsg, T data) {
        this.code = code;
        this.message = resultMsg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", resultMsg='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
