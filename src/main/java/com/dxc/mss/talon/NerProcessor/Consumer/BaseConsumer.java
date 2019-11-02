package com.dxc.mss.talon.NerProcessor.Consumer;

import com.rabbitmq.client.*;
import java.io.IOException;

abstract public class BaseConsumer implements Consumer {

    /** Consumer tag for this consumer. */
    private volatile String _consumerTag;

    private String _queue;
    private Channel _channel;

    BaseConsumer(String queue, Connection connection) throws IOException {

        _queue = queue;
        _channel = connection.createChannel();
        _channel.queueDeclare(queue,true,false,false, null);

    }

    @Override
    public void handleConsumeOk(String consumerTag) {
        _consumerTag = consumerTag;
    }

    @Override
    public void handleCancelOk(String consumerTag) {

    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

    }

    @Override
    public void handleRecoverOk(String consumerTag) {

    }

    public String getQueue() {
        return _queue;
    }

    public void consume() throws IOException {
        _channel.basicConsume(_queue, true, this);
    }
}
