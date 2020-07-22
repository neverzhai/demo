package com.shuanger.demo.filterinterceptor.response;

public enum RespCode {

    SUCCESS("00000","调用成功"),
    FAILED("10000","调用失败"),
    ;



    private final String code;
    private String message;

    RespCode(String code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public String getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
