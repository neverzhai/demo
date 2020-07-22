package com.shuanger.demo.filterinterceptor.response;



public class BusinessException extends RuntimeException{

    private RespCode respCode;

    private String msg;

    public BusinessException(RespCode respCode){
        this.msg = respCode.getMessage();
        this.respCode = respCode;
    }


    public RespCode getRespCode() {
            return respCode;
    }

    public String getMsg() {
        return msg;
    }
}
