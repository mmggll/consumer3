package com.zr.config;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by 86151 on 2019/3/8.
 */
@FeignClient(value = "provider")
public interface FeignProxy {
    @GetMapping("/providerTest")
    public String test();

    @GetMapping("/providerTest1")
    public User test1();

    @GetMapping("/providerTest2")
    public List<User> test2(@RequestParam("id") Integer id);

    @PostMapping("/providerPost")
    public User test1(@RequestBody User user);
}
