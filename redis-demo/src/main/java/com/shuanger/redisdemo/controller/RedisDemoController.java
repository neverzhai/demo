package com.shuanger.redisdemo.controller;

import com.shuanger.redisdemo.cache.RedisSelfServiceImpl;
import com.shuanger.redisdemo.domain.Person;
import com.shuanger.redisdemo.entity.Person2;
import com.shuanger.redisdemo.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-25 16:41
 * @description:
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisDemoController {

    @Resource
    private RedisSelfServiceImpl redisSelfService;

    @RequestMapping("/string")
    public Boolean setString() {
        Boolean set = redisSelfService.set("stringSet", "shuanger3");

        return set;
    }

    @RequestMapping("/string/get")
    public Object getString() {
        return redisSelfService.get("stringSet");
    }

    @RequestMapping("/object")
    public Boolean setObject() {
        Boolean set = redisSelfService.set("objectSet", new Person("testName", 21L));

        return set;
    }

    @RequestMapping("/genericJackJson/get")
    public Object getGenericObject() {
        Person objectSet = (Person)redisSelfService.get("objectSet");

        return objectSet;
    }

    @RequestMapping("/genericJackJson/get/package")
    public Object getObject() {
        Person2 objectSet = (Person2)redisSelfService.get("objectSet");
        return objectSet;
    }


    @RequestMapping("/jackJson/get")
    public Object getJackJsonObject() {
        Object objectSet = redisSelfService.get("objectSet");
        String toJson = GsonUtils.toJson(objectSet);
        Person person = GsonUtils.fromJson(toJson, Person.class);

        return person;
    }
}
