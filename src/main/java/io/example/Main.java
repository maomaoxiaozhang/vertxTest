package io.example;

import io.example.eventbus.EventBusConsumer;
import io.example.eventbus.EventBusProducer;
import io.vertx.core.Vertx;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author shouchang.zgq
 * @date 2018/08/15
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(EventBusProducer.class.getName());
        vertx.deployVerticle(EventBusConsumer.class.getName());
    }
}
