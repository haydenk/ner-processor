package com.dxc.mss.talon.NerProcessor.Consumer;

import com.rabbitmq.client.*;
import java.io.IOException;

public class NerProcesserConsumer extends BaseConsumer {

    public NerProcesserConsumer(Connection connection) throws IOException {
        super("NerParserConsumer", connection);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(properties.getExpiration());
        System.out.println(" [x] Received in '" + this.getQueue() + "' '" + message + "'");
    }
}
