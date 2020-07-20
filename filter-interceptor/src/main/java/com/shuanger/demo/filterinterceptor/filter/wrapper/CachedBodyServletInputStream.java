package com.shuanger.demo.filterinterceptor.filter.wrapper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-24 08:24
 * @description:
 */
public class CachedBodyServletInputStream  extends ServletInputStream {
    private ServletInputStream cachedBodyInputStream;

    public CachedBodyServletInputStream(ServletInputStream inputStream) {
        cachedBodyInputStream = inputStream;
    }

    @Override
    public boolean isFinished() {
        return cachedBodyInputStream.isFinished();
    }

    @Override
    public boolean isReady() {
        return cachedBodyInputStream.isReady();
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        cachedBodyInputStream.setReadListener(readListener);
    }

    @Override
    public int read() throws IOException {
      return cachedBodyInputStream.read();
    }
}