package com.shuanger.doctool.domain;

import lombok.Data;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-03 10:41
 * @description:
 */
@Data
public class PostmanRequest {

    private String method;

    private PostmanRequestBody requestBody;

    private PostmanRequestUrl requestUrl;

}
