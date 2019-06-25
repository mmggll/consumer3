package com.zr.controller;

import com.zr.config.FeignProxy;
import com.zr.config.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 86151 on 2019/3/8.
 */
@RestController
public class FeignController {
    @Autowired
    private FeignProxy feignProxy;

    @GetMapping("/testFeign")
    public String feignTest(){
        return feignProxy.test();
    }

    @GetMapping("/testFeign1")
    public User feignTest1(){
        return feignProxy.test1();
    }

    @PostMapping("/testFeign2")
    public User feignTest2(@RequestBody User user){
        return feignProxy.test1(user);
    }

    @GetMapping("/testFeignPost")
    public List<User> testFeignPost(@RequestParam("id")Integer id){
        return feignProxy.test2(id);
    }
}
