package com.shuanger.doctool.controller;

import com.shuanger.doctool.request.CreateDocumentRequest;
import com.shuanger.doctool.service.DocumentToolService;
import com.shuanger.doctool.service.POIDocumentService;
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

    @Resource
    private POIDocumentService poiDocumentService;

    @RequestMapping("/create")
    public Boolean createDoc(@RequestBody @Validated CreateDocumentRequest request) {

        documentToolService.createDocx(request.getItem(), request.getTitle());
        return true;
    }


    @RequestMapping("/poi/test")
    public void writeTOC() throws Exception {
        poiDocumentService.writeTOC();
    }
}
