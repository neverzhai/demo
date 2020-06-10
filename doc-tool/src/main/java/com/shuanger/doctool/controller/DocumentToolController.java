package com.shuanger.doctool.controller;

import com.shuanger.doctool.request.CreateDocumentRequest;
import com.shuanger.doctool.service.DocumentToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-03 10:20
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/doc")
public class DocumentToolController {

    @Resource
    private DocumentToolService documentToolService;

    @RequestMapping("/create")
    public Boolean createDoc(@RequestBody @Validated CreateDocumentRequest request) {

        documentToolService.createDocx(request.getItems(), request.getTitle());
        return true;
    }
}
