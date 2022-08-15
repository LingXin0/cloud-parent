package com.dx.cloud.config;

import com.dx.cloud.listener.MyListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author DongXin
 * @date 2022/6/21 3:44 下午
 */
@Component
public class RabbitmqConfigUtil {

    private static final Map<String, DirectMessageListenerContainer> CONTAINER_MAP = new ConcurrentHashMap<>(8);

    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private DirectExchange directExchange;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void addQueueAndExchange(String queueName, String routingKey) {
        Queue queue = new Queue(queueName);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareExchange(directExchange);
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(routingKey));
    }

    public void startListener(String queueName, String routingKey, int consumerNum) {
        addQueueAndExchange(queueName, routingKey);
        DirectMessageListenerContainer container = new DirectMessageListenerContainer(
                rabbitTemplate.getConnectionFactory());

        DirectMessageListenerContainer existsContainer = CONTAINER_MAP.putIfAbsent(queueName, container);
        if (null != existsContainer) {
            container = existsContainer;
        } else {
            container.setQueueNames(queueName);
        }
        container.setPrefetchCount(consumerNum);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setConsumersPerQueue(consumerNum);
        container.setMessageListener(new MyListener());
        container.setAdviceChain(createRetry());
        container.setDefaultRequeueRejected(false);
        container.start();
    }

    /**
     * 重试机制
     *
     * @return
     */
    private RetryOperationsInterceptor createRetry() {
        return RetryInterceptorBuilder.stateless()
                // 重试次数
                .maxAttempts(3)
                // 重试间隔 指数递增时间参数 最大间隔时间
                .backOffOptions(1000, 3, 5000)
                // 次数用完之后的处理,用的是默认处理类,失败消息会到死信
                .recoverer(new RejectAndDontRequeueRecoverer()).build();
    }
}
