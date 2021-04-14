package com.shuanger.httpdemo.controller;

import com.shuanger.httpdemo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-04-14 15:24
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/http")
public class HttpDemoController {

    // HTTP 开启压缩实验
    @GetMapping("/test")
    public Object testSentSys()  {

        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i< 1024; i++) {
            User user = new User();
            user.setUserId("12487erfuwdffryeyreg")
                    .setAddress("北京通州区, 亦庄")
                    .setEmail("zhaixiaoshung@jd.com")
                    .setGender(4)
                    .setMobile("123456789")
                    .setNickName("feicaiguniang"+i)
                    .setCreatedTime(LocalDateTime.now());

            users.add(user);
        }


        return users;
    }
}
