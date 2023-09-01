package com.es.dx.cloud.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DongXin
 * @date 2022/6/16 2:28 下午
 * <p>
 * Topic类型（拓展匹配发送）
 */
@Configuration
public class RabbitTopicConfig {

    @Bean
    public Queue topicQueue() {
        //1.队列名 2.是否持久化 3.是否独占 4.自动删除 5.其他参数
        return new Queue("topicQueue", false, false, false, null);
    }

    @Bean
    public Binding bingTopicExchange(TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue()).to(topicExchange).with("*.Two.*");
    }
}
