package com.pl.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author plei
 * @date 2020/4/2
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        //随机 IRule有7种轮询方式
        return new RandomRule();
    }


}
