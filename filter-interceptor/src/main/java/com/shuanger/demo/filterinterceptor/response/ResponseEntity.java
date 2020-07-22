package com.shuanger.demo.filterinterceptor.response;


public class ResponseEntity {

    private String code;

    private String message;

    private Object data;


    public ResponseEntity(RespCode rc, Object data) {
        this.code = rc.getCode();
        this.message = rc.getMessage();
        this.data = data;
    }

    public ResponseEntity(RespCode rc){
        this.code = rc.getCode();
        this.message = rc.getMessage();
    }

    public ResponseEntity(String code, String msg){
        this.code = code;
        this.message = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
