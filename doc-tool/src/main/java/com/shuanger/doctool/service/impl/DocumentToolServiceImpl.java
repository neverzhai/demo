package com.shuanger.doctool.service.impl;

import com.shuanger.doctool.domain.PostmanItem;
import com.shuanger.doctool.domain.PostmanRequest;
import com.shuanger.doctool.request.CreateDocumentRequest;
import com.shuanger.doctool.service.DocumentToolService;
import com.shuanger.doctool.util.WordUtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-09 11:32
 * @description: document 工具实现类
 */
@Slf4j
@Service
public class DocumentToolServiceImpl implements DocumentToolService {

    @Resource
    private WordUtilService wordUtilService;

    public void createDocx(List<PostmanItem> postmanItems, String title) {

        wordUtilService.initWordWriterWithTitle(title);

        for (int index = 0; index < postmanItems.size();  index ++ ) {

            PostmanItem item = postmanItems.get(index);
            wordUtilService.writeName(index, item.getName());
            this.writeRequest(item.getRequest());
        }

        wordUtilService.flushAndClose(title);

    }

    private void writeRequest(PostmanRequest request) {
        switch (request.getMethod()) {
            case "GET":
                wordUtilService.writeGetRequest(request);
                break;
            case "POST":
                wordUtilService.writePostRequest(request);
                break;
                default:
                    break;
        }

    }
}
