package com.dx.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author DongXin
 * @date 2022/6/15 5:57 下午
 */
@SpringBootApplication
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}