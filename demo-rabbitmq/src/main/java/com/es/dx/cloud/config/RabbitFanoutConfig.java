package com.es.dx.cloud.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DongXin
 * @date 2022/6/16 2:37 下午
 * <p>
 * Fanout 类型（广播发送）
 */
@Configuration
public class RabbitFanoutConfig {

    @Bean
    public Queue fanoutQueue() {
        //参数介绍
        //1.队列名 2.是否持久化 3.是否独占 4.自动删除 5.其他参数
        return new Queue("fanoutQueue-One", false, false, false, null);
    }

    @Bean
    public Binding bingFanoutExchange(FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange);
    }
}
