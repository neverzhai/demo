package com.shuanger.wechat.response;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseResult customerExceptionHandler(BusinessException e) {
        ResponseResult rr;
        rr = ResponseResult.create(e.getCode(), e.getMessage());
        return rr;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseResult IllegalExceptionHandler(IllegalArgumentException e) {
        ResponseResult rr;
        rr = ResponseResult.create(BusinessCode.SERVER_ERROR.getCode(), e.getMessage());
        return rr;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult dealHibernateException(Exception exception, HttpServletRequest request) {
        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
            return ResponseResult.create(BusinessCode.PARAM_ERROR.getCode(), msg);
        } else {
            msg = "系统繁忙，请稍后重试...";
            return ResponseResult.create(BusinessCode.SERVER_ERROR.getCode(), msg);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        ResponseResult rr;
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {

            rr = ResponseResult.create(BusinessCode.SC_NOT_FOUND);
        } else {
            rr = ResponseResult.create(BusinessCode.SERVER_ERROR);
        }

        log.error("系统异常 ：", e);
        return rr;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult paramExceptionHandler(HttpMessageNotReadableException e) {
        log.error("参数解析异常： ", e);
        return ResponseResult.create(BusinessCode.PARAM_ERROR);
    }
}
