package com.shuanger.demo.filterinterceptor.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class CustomRequestWrapper extends HttpServletRequestWrapper {



    public CustomRequestWrapper(HttpServletRequest request) {
        super(request);
        String strFromInputStream = getContentFromServletRequest();
    }

//    @Override
//    public BufferedReader getReader() throws IOException {
//        BufferedReader br;
////        String decodeJson = decode();
////        br = new BufferedReader(new InputStreamReader(new CustomServletInputStream(decodeJson)));
//        return br;
//    }

//    @Override
//    public ServletInputStream getInputStream() throws IOException {
//        ServletInputStream inputStream;
////        String decodeJson = decode();
////        inputStream = new CustomServletInputStream(decodeJson);
////        return inputStream;
//    }


    /**
     * 从ServletRequest中的InputStream读取内容，
     * 【注意】该方法只能调用一次，因为流只能被读一次！！！
     */
    private String getContentFromServletRequest() {
        String content = null;
        ServletInputStream inputStream = null;
        try {
            inputStream = super.getInputStream();
            content = StreamUtils.streamToString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
        return content;
    }


    private class CustomServletInputStream extends ServletInputStream {

        private ByteArrayInputStream buffer;

        public CustomServletInputStream(String body) {
            body = body == null ? "" : body;
            this.buffer = new ByteArrayInputStream(body.getBytes());
        }

        @Override
        public int read() throws IOException {

            return buffer.read();
        }

        @Override
        public boolean isFinished() {

            return buffer.available() == 0;
        }

        @Override
        public boolean isReady() {

            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new RuntimeException("Not implemented");
        }
    }
}