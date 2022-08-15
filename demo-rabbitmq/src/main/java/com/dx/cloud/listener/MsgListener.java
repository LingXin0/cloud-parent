package com.dx.cloud.listener;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author DongXin
 * @date 2022/6/21 1:50 下午
 */
@Component
public class MsgListener {

    @RabbitListener(queues = "directQueue")
    //@RabbitListener(queuesToDeclare = @Queue(value = "directQueue", durable = "true"))
    public void receive(String message) {
        System.out.println("Received: " + message);
    }
}
