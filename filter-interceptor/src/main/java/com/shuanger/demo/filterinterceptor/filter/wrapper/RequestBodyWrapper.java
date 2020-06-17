package com.shuanger.demo.filterinterceptor.filter.wrapper;

import com.shuanger.demo.filterinterceptor.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.*;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-11 18:13
 * @description:
 */
@Slf4j
public class RequestBodyWrapper extends HttpServletRequestWrapper {

    private String body;
    private Map<String, String> headerMap = new HashMap<>();
    private Map<String, String> parameterMap = new HashMap<>();

    public RequestBodyWrapper(HttpServletRequest request) {
        super(request);
        body = readBody(request);
        readHeader(request);
    }

    private void readHeader(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String headerValue = request.getHeader(name);

            headerMap.put(name, headerValue);
        }
    }

    private String readBody(HttpServletRequest request) {
        if (isFormContentType(request.getContentType())) {
            return parseParameterValue(request);
        }

        StringBuilder stringBuilder = parseRequestBody(request);

        String string = stringBuilder.toString();

        return string;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }
            @Override
            public boolean isReady() {
                return false;
            }
            @Override
            public void setReadListener(ReadListener readListener) {
            }
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;

    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        return headerMap;
    }


    @Override
    public String getHeader(String name) {
        String headerValue = super.getHeader(name);
        if (headerMap.containsKey(name)) {
            headerValue = headerMap.get(name);
        }
        return headerValue;
    }


    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = new ArrayList<>();
        for (String name : headerMap.keySet()) {
            names.add(name);
        }
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> values = new ArrayList<>();
        if (headerMap.containsKey(name)) {
            values.add(headerMap.get(name));
        }
        return Collections.enumeration(values);
    }

    private boolean isFormContentType(String contentType) {
        log.info("当前请求的contentType: {}",contentType);
        if (StringUtils.isEmpty(contentType)) {
            return false;
        }

        MediaType mediaType = MediaType.parseMediaType(contentType);
        if (MediaType.APPLICATION_FORM_URLENCODED.includes(mediaType)) {
            return true;
        }
        return false;
    }

    private String parseParameterValue(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String nextElement = parameterNames.nextElement();
            String parameter = request.getParameter(nextElement);

            parameterMap.put(nextElement, parameter);
        }
        return GsonUtils.toJson(parameterMap);
    }

    private StringBuilder parseRequestBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }

        } catch (IOException ex) {
            log.info("读取request body出现异常");

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    log.info("读取request body, 关闭流出现异常");
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException e) {
                    log.info("读取request body, 关闭出现异常");
                }
            }
        }
        return stringBuilder;
    }
}
