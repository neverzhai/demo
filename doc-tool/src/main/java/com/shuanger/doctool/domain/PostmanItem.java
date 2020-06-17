package com.shuanger.doctool.domain;

import lombok.Data;

import java.util.List;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-03 10:24
 * @description:
 */
@Data
public class PostmanItem {

    private String name;

    private PostmanRequest request;

    private List<String> response;

}
