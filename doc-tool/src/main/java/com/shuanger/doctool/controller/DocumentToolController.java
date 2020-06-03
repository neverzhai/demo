package com.shuanger.doctool.controller;

import com.shuanger.doctool.domain.PostmanItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-03 10:20
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/doc")
public class DocumentToolController {

    @RequestMapping("/create")
    public Boolean createDoc(@RequestBody List<PostmanItem> postmanItems) {

        return true;
    }
}
