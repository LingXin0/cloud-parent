package com.dx.cloud.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DongXin
 * @date 2022/6/16 11:56 上午
 * <p>
 * Direct类型（默认，匹配发送）
 */
@Configuration
public class RabbitDirectConfig {

    @Bean
    public Queue directQueue() {
        //1.队列名 2.是否持久化 3.是否独占 4.自动删除 5.其他参数
        return new Queue("directQueue", false, false, false, null);
    }

    @Bean
    public Binding bingDirectExchange(DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue()).to(directExchange).with("One");
    }

}
