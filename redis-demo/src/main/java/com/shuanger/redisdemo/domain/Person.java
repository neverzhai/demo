package com.shuanger.redisdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-25 16:44
 * @description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person implements Serializable {

    private String name;

    private Long age;
}
