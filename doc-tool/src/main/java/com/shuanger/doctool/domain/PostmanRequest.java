package com.shuanger.doctool.domain;

import lombok.Data;

import java.util.List;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-03 10:41
 * @description:
 */
@Data
public class PostmanRequest {

    private String method;

    private List<PostmanRequestHeader> header;

    private PostmanRequestBody body;

    private PostmanRequestUrl url;

}
