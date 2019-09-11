package com.cc.dugumashen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description：
 * Date: 2019/8/30
 * Author：
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cc.dugumashen"})
public class SpringbootApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplicationStarter.class,args);
    }
}
