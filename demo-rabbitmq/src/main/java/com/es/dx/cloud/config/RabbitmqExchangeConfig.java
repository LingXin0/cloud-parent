package com.es.dx.cloud.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DongXin
 * @date 2022/6/21 2:51 下午
 * <p>
 * 交换机配置
 */
@Configuration
public class RabbitmqExchangeConfig {

    @Bean
    public DirectExchange directExchange() {
        //1.交换器名 2.是否持久化 3.自动删除 4.其他参数
        return new DirectExchange("directExchange", false, false, null);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        //参数介绍
        //1.交换器名 2.是否持久化 3.自动删除 4.其他参数
        return new FanoutExchange("Fanout-Ex", false, false, null);
    }

    @Bean
    public TopicExchange topicExchange() {
        //1.交换器名 2.是否持久化 3.自动删除 4.其他参数
        return new TopicExchange("topicExchange", false, false, null);
    }

    @Bean
    public HeadersExchange headersExchange() {
        //参数介绍
        //1.交换器名 2.是否持久化 3.自动删除 4.其他参数
        return new HeadersExchange("Headers-Ex", false, false, null);
    }
}
