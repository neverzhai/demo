package com.shuanger.demo.filterinterceptor.filter.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-24 08:01
 * @description:  使用getReader获取body
 */

@Slf4j
public class GetReaderWrapper  extends HttpServletRequestWrapper {

    private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";

    private final ByteArrayOutputStream cachedContent;

    @Nullable
    private final Integer contentCacheLimit;

    @Nullable
    private ServletInputStream inputStream;

    @Nullable
    private BufferedReader reader;

    public GetReaderWrapper(HttpServletRequest request) {
        super(request);
        int contentLength = request.getContentLength();
        this.cachedContent = new ByteArrayOutputStream(contentLength >= 0 ? contentLength : 1024);
        this.contentCacheLimit = null;
    }


}