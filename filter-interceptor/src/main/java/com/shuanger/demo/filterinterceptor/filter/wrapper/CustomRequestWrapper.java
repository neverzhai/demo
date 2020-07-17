package com.shuanger.demo.filterinterceptor.filter.wrapper;

import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-28 16:27
 * @description:
 */
public class CustomRequestWrapper extends HttpServletRequestWrapper {

    private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private final ByteArrayOutputStream cachedContent;
    @Nullable
    private final Integer contentCacheLimit;
    @Nullable
    private ServletInputStream inputStream;
    @Nullable
    private BufferedReader reader;
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public CustomRequestWrapper(HttpServletRequest request) {
        super(request);
        int contentLength = request.getContentLength();
        this.cachedContent = new ByteArrayOutputStream(contentLength >= 0 ? contentLength : 1024);
        this.contentCacheLimit = null;
    }

    public CustomRequestWrapper(HttpServletRequest request, int contentCacheLimit) {
        super(request);
        this.cachedContent = new ByteArrayOutputStream(contentCacheLimit);
        this.contentCacheLimit = contentCacheLimit;
    }

    public ServletInputStream getInputStream() throws IOException {
        if (this.inputStream == null) {
            this.inputStream = new CustomRequestWrapper.CustomCachingInputStream(this.getRequest().getInputStream());
        }

        return this.inputStream;
    }

    public String getCharacterEncoding() {
        String enc = super.getCharacterEncoding();
        return enc != null ? enc : "ISO-8859-1";
    }

    public BufferedReader getReader() throws IOException {
        if (this.reader == null) {
            this.reader = new BufferedReader(new InputStreamReader(this.getInputStream(), this.getCharacterEncoding()));
        }

        return this.reader;
    }

    public String getParameter(String name) {
        if (this.cachedContent.size() == 0 && this.isFormPost()) {
            this.writeRequestParametersToCachedContent();
        }

        return super.getParameter(name);
    }

    public Map<String, String[]> getParameterMap() {
        if (this.cachedContent.size() == 0 && this.isFormPost()) {
            this.writeRequestParametersToCachedContent();
        }

        return super.getParameterMap();
    }

    public Enumeration<String> getParameterNames() {
        if (this.cachedContent.size() == 0 && this.isFormPost()) {
            this.writeRequestParametersToCachedContent();
        }

        return super.getParameterNames();
    }

    public String[] getParameterValues(String name) {
        if (this.cachedContent.size() == 0 && this.isFormPost()) {
            this.writeRequestParametersToCachedContent();
        }

        return super.getParameterValues(name);
    }

    private boolean isFormPost() {
        String contentType = this.getContentType();
        return contentType != null && contentType.contains("application/x-www-form-urlencoded") && HttpMethod.POST.matches(this.getMethod());
    }

    private void writeRequestParametersToCachedContent() {
        try {
            if (this.cachedContent.size() == 0) {
                String requestEncoding = this.getCharacterEncoding();
                Map<String, String[]> form = super.getParameterMap();
                Iterator nameIterator = form.keySet().iterator();

                while(nameIterator.hasNext()) {
                    String name = (String)nameIterator.next();
                    List<Object> values = Arrays.asList((Object[])form.get(name));
                    Iterator valueIterator = values.iterator();

                    while(valueIterator.hasNext()) {
                        String value = (String)valueIterator.next();
                        this.cachedContent.write(URLEncoder.encode(name, requestEncoding).getBytes());
                        if (value != null) {
                            this.cachedContent.write(61);
                            this.cachedContent.write(URLEncoder.encode(value, requestEncoding).getBytes());
                            if (valueIterator.hasNext()) {
                                this.cachedContent.write(38);
                            }
                        }
                    }

                    if (nameIterator.hasNext()) {
                        this.cachedContent.write(38);
                    }
                }
            }

        } catch (IOException var8) {
            throw new IllegalStateException("Failed to write request parameters to cached content", var8);
        }
    }

    public byte[] getContentAsByteArray() {
        return this.cachedContent.toByteArray();
    }


    private void handleContentOverflow(Integer contentCacheLimit) {

    }


    private class CustomCachingInputStream extends ServletInputStream {
        private final ServletInputStream is;

        private boolean overflow = false;

        public CustomCachingInputStream(ServletInputStream inputStream) {
            this.is = inputStream;
        }

        @Override
        public boolean isFinished() {
            return this.is.isFinished();
        }

        @Override
        public boolean isReady() {
            return this.is.isReady();
        }
        @Override
        public void setReadListener(ReadListener listener) {
            this.is.setReadListener(listener);
        }

        public int read() throws IOException {
            int ch = this.is.read();
            if (ch != -1 && !this.overflow) {
                if (CustomRequestWrapper.this.contentCacheLimit != null && CustomRequestWrapper.this.cachedContent.size() == CustomRequestWrapper.this.contentCacheLimit) {
                    this.overflow = true;
                    CustomRequestWrapper.this.handleContentOverflow(CustomRequestWrapper.this.contentCacheLimit);
                } else {
                    CustomRequestWrapper.this.cachedContent.write(ch);
                }
            }

            return ch;
        }

        public int read(byte[] b) throws IOException {
            int count = this.is.read(b);
            this.writeToCache(b, 0, count);
            return count;
        }

        private void writeToCache(byte[] b, int off, int count) {
            if (!this.overflow && count > 0) {
                if (CustomRequestWrapper.this.contentCacheLimit != null && count + CustomRequestWrapper.this.cachedContent.size() > CustomRequestWrapper.this.contentCacheLimit) {
                    this.overflow = true;
                    CustomRequestWrapper.this.cachedContent.write(b, off, CustomRequestWrapper.this.contentCacheLimit - CustomRequestWrapper.this.cachedContent.size());
                    CustomRequestWrapper.this.handleContentOverflow(CustomRequestWrapper.this.contentCacheLimit);
                    return;
                }

                CustomRequestWrapper.this.cachedContent.write(b, off, count);
            }

        }

        public int read(byte[] b, int off, int len) throws IOException {
            int count = this.is.read(b, off, len);
            this.writeToCache(b, off, count);
            return count;
        }

        public int readLine(byte[] b, int off, int len) throws IOException {
            int count = this.is.readLine(b, off, len);
            this.writeToCache(b, off, count);
            return count;
        }
    }
}
