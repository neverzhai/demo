package com.shuanger.interceptor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Callable;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-13 18:44
 * @description: 异步请求的测试controller
 */
@Slf4j
@RestController
@RequestMapping("/asyncTest")
public class AsyncTestController {


    // 方式一  方式实现异步请求
    @RequestMapping(value = "/email/servletReq")
    public void servletReq (HttpServletRequest request, HttpServletResponse response) {
        final AsyncContext asyncContext = request.startAsync();
        //设置监听器:可设置其开始、完成、异常、超时等事件的回调处理
        asyncContext.addListener(new AsyncListener() {
            public void onTimeout(AsyncEvent event) throws IOException {
                System.out.println("超时了...");
                //做一些超时后的相关操作...
            }
            public void onStartAsync(AsyncEvent event) throws IOException {
                System.out.println("线程开始");
            }
            public void onError(AsyncEvent event) throws IOException {
                System.out.println("发生错误："+event.getThrowable());
            }
            public void onComplete(AsyncEvent event) throws IOException {
                System.out.println("执行完成");
                //这里可以做一些清理资源的操作...
            }
        });
        //设置超时时间
        asyncContext.setTimeout(20000);
        asyncContext.start(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(10000);
                    System.out.println("内部线程：" + Thread.currentThread().getName());
                    asyncContext.getResponse().setCharacterEncoding("utf-8");
                    asyncContext.getResponse().setContentType("text/html;charset=UTF-8");
                    asyncContext.getResponse().getWriter().println("这是异步的请求返回");
                } catch (Exception e) {
                    System.out.println("异常："+e);
                }
                //异步请求完成通知
                //此时整个请求才完成
                asyncContext.complete();
            }
        });
        //此时之类 request的线程连接已经释放了
        System.out.println("主线程：" + Thread.currentThread().getName());
    }

    // 方式二: 使用很简单，直接返回的参数包裹一层callable即可，可以继承WebMvcConfigurerAdapter类来设置默认线程池和超时处理
    @RequestMapping(value = "/email/callableReq")
    @ResponseBody
    public Callable<String> callableReq () {
        System.out.println("外部线程：" + Thread.currentThread().getName());

        return new Callable<String>() {

            public String call() throws Exception {
                Thread.sleep(10000);
                System.out.println("内部线程：" + Thread.currentThread().getName());
                return "callable!";
            }
        };
    }
}
