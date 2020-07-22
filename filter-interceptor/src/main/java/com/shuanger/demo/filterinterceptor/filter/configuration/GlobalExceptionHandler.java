package com.shuanger.demo.filterinterceptor.filter.configuration;

import com.shuanger.demo.filterinterceptor.response.BusinessException;
import com.shuanger.demo.filterinterceptor.response.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 *
 * Description:全局异常处理器
 *
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity dealBizException(BusinessException e) {
        log.info("业务异常:   "+e.getMsg());
        return new ResponseEntity(e.getRespCode().getCode(),e.getMsg());
    }


}
