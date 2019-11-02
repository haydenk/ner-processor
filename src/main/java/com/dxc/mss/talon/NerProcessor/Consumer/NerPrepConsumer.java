package com.dxc.mss.talon.NerProcessor.Consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class NerPrepConsumer extends BaseConsumer {
    public NerPrepConsumer(Connection connection) throws IOException {
        super("NerPrepConsumer", connection);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(properties.getExpiration());
        System.out.println(" [x] Received in '" + this.getQueue() + "' '" + message + "'");
    }
}
