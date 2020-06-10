package com.shuanger.doctool.domain;

import lombok.Data;

import java.util.List;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-03 10:43
 * @description:
 */
@Data
public class PostmanRequestBody {

    private String mode;

    private String raw;

    private List<FormDataBody> formdata;

}
