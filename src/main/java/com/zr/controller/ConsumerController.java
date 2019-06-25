package com.zr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 马国龙 on 2019/3/7.
 */
@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("consumerTest")
    public String test(@RequestParam("count") Integer count) {
        String forObject = null;
        if (count == 123) {
            forObject = restTemplate.getForObject("http://127.0.0.1:8030/providerTest", String.class);
        } else {
            forObject = restTemplate.getForObject("http://127.0.0.1:8040/providerTest", String.class);
        }
        return forObject;
    }
}
