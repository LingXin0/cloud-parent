package com.dx.cloud.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author DongXin
 * @date 2022/6/21 2:34 下午
 */
public class MyListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("Received: " + message);
    }
}
