package com.dx.cloud.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DongXin
 * @date 2022/6/15 6:06 下午
 */

@RestController
@RequestMapping("/rabbit")
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/direct/convertAndSend")
    public String test(@RequestParam("message") String message) {
        rabbitTemplate.convertAndSend("directExchange", "One", message);
        return "test";
    }
}
