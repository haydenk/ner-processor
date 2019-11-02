package com.dxc.mss.talon.NerProcessor;

import com.dxc.mss.talon.NerProcessor.Consumer.BaseConsumer;
import com.dxc.mss.talon.NerProcessor.Consumer.NerPrepConsumer;
import com.dxc.mss.talon.NerProcessor.Consumer.NerProcesserConsumer;
import com.rabbitmq.client.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

public class Application {
    public static void main(String[] argv) throws TimeoutException {

        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");
            properties.load(input);

            try {
                ConnectionFactory factory = new ConnectionFactory();
                factory.setHost(properties.getProperty("rabbitmq.host"));
                factory.setUsername(properties.getProperty("rabbitmq.username"));
                factory.setPassword(properties.getProperty("rabbitmq.password"));

                System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

                List<BaseConsumer> consumers = Arrays.asList(
                        new NerProcesserConsumer(factory.newConnection()),
                        new NerPrepConsumer(factory.newConnection())
                );

                consumers.forEach(c -> {
                    try {
                        c.consume();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
