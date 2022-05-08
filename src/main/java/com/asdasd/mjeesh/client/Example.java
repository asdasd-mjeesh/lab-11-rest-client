package com.asdasd.mjeesh.client;

import com.asdasd.mjeesh.client.communication.ProducerCommunication;
import com.asdasd.mjeesh.client.communication.ProductCommunication;
import com.asdasd.mjeesh.client.configuration.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Example {

    public static void main(String[] args) {
        new Example().run();
    }

    private void run() {
        try (var context = new AnnotationConfigApplicationContext(Config.class)) {
            var productCommunication = context.getBean("productCommunication", ProductCommunication.class);
            var producerCommunication = context.getBean("producerCommunication", ProducerCommunication.class);



        }
    }
}
