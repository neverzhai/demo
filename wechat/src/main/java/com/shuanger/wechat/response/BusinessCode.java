package com.shuanger.wechat.response;

import lombok.Getter;


public enum BusinessCode {

    SUCCESS("00000", "操作成功"),

    SERVER_ERROR("10000", "服务处理失败，请稍后重试~"),
    SC_NOT_FOUND("10001", "服务不存在"),
    DUPLICATE_REQUEST("10002", "请勿重复操作"),
    UNEXPECTED_DATA_OPERATION("11000", "数据查询失败"),

    USER_NOT_LOGIN("20001", "用户未登录"),
    USER_NOT_FOUND("20002", "用户不存在"),

    PARAM_ERROR("30001", "参数异常"),
    VERIFY_CODE_INCORRECT("30002", "验证码错误"),
    DATA_NOT_EXISTS("30010", "不存在数据"),
    ;

    @Getter
    private String code;
    @Getter
    private String msg;

    BusinessCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean codeEqual(String cmpCode) {
        return this.code.equals(cmpCode);
    }

    public boolean codeNotEqual(String cmpCode) {
        return !this.codeEqual(cmpCode);
    }
}
