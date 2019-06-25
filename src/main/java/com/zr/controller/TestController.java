package com.zr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 86151 on 2019/3/7.
 */
@RestController
public class TestController {



/*
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/consumerTest")
    public String test(){
        String forObject = restTemplate.getForObject("http://127.0.0.1:8030/providerTest", String.class);
        return forObject;
    }
*/

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/consumerTest")
    public String test(){
        String forObject = restTemplate.getForObject("http://PROVIDER/providerTest", String.class);

        return forObject;
    }
}
