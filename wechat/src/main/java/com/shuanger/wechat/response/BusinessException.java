package com.shuanger.wechat.response;


import lombok.Getter;
import lombok.Setter;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private String code;

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(BusinessCode code, String message) {
        super(message);
        this.code = code.getCode();
    }

    public BusinessException(BusinessCode code) {
        this(code.getCode(), code.getMsg());
    }
}

