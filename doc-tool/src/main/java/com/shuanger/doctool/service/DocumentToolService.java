package com.shuanger.doctool.service;

import com.shuanger.doctool.domain.PostmanItem;

import java.util.List;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-09 11:31
 * @description:
 */
public interface DocumentToolService {
    void createDocx(List<PostmanItem> postmanItems);
}
