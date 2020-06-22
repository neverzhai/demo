package com.shuanger.wechat.client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-22 15:50
 * @description:
 */
public class WechatClient {

    @Resource
    private RestTemplate restTemplate;

    public Object getGoodsCouponTypeList() {

        ResponseResult respEntity = sendGetRequest("/goodsCoupon/typeList");

        return respEntity;
    }


    public Object getGoodsByType (GoodsListRequest listRequest) {
        ResponseResult responseResult = sendPostRequest("/goodsCoupon/list", listRequest);

        return responseResult;

    }

    public Object getCommonSearch() {
        CommonSearchRequest request = new CommonSearchRequest("OPG001129", "1");

        ResponseResult respEntity = sendPostRequest("/page/commonsearch", request);

        return respEntity;
    }

    private ResponseResult sendPostRequest(String url, Object request) {

        HttpHeaders headers = buildHttpHeaders(request);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data", GsonUtils.toJson(request));

        HttpEntity<Object> httpEntity = new HttpEntity<>(GsonUtils.toJson(hashMap), headers);

        String requestUrl = BASE_URL + url;
        ResponseEntity<ResponseResult> respEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, httpEntity, ResponseResult.class);

        ResponseResult body = respEntity.getBody();

        return body;
    }

    private ResponseResult sendGetRequest(String url, Object ...variables) {
        HttpHeaders headers = buildHttpHeaders(null);

        String requestUrl = BASE_URL + url;

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<ResponseResult> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, httpEntity, ResponseResult.class);

        return responseEntity.getBody();

    }


    private HttpHeaders buildHttpHeaders(Object data) {


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());

        return httpHeaders;
    }
}
