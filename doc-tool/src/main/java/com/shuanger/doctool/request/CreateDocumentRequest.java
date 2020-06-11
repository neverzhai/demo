package com.shuanger.doctool.request;

import com.shuanger.doctool.domain.PostmanItem;
import lombok.Data;

import java.util.List;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-10 11:59
 * @description:
 */
@Data
public class CreateDocumentRequest {

    private String title;

    private List<PostmanItem> item;
}
