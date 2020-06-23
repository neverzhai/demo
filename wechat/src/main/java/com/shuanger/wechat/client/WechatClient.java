//package com.shuanger.wechat.client;
//
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//
///**
// * @author: zhaixiaoshuang
// * @date: 2020-06-22 15:50
// * @description:
// */
//public class WechatClient {
//
//    @Resource
//    private RestTemplate restTemplate;
//
//
//
//    private Object sendPostRequest(String url, Object request) {
//
//        HttpHeaders headers = buildHttpHeaders(request);
//
//        HttpEntity<Object> httpEntity = new HttpEntity<Object>(request, headers);
//
//        String requestUrl = BASE_URL + url;
//        ResponseEntity<Object> respEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, httpEntity, Object.class);
//
//        Object body = respEntity.getBody();
//
//        return body;
//    }
//
//    private ResponseResult sendGetRequest(String url, Object ...variables) {
//        HttpHeaders headers = buildHttpHeaders(null);
//
//        String requestUrl = BASE_URL + url;
//
//        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
//
//        ResponseEntity<ResponseResult> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, httpEntity, ResponseResult.class);
//
//        return responseEntity.getBody();
//
//    }
//
//
//    private HttpHeaders buildHttpHeaders(Object data) {
//
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
//
//        return httpHeaders;
//    }
//}
