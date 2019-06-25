package com.zr.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by 86151 on 2019/3/7.
 */
@Configuration
public class RandomMySelf {
    @Bean
    public IRule myRule(){
        return new RandomLoadBlanced();
//        return new RoundRobinRule();
    }
}
