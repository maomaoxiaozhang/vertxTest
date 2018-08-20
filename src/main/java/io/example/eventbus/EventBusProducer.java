package io.example.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/**
 * @author shouchang.zgq
 * @date 2018/08/16
 */
public class EventBusProducer extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new EventBusProducer());
    }

    @Override
    public void start() {
        EventBus eventBus = vertx.eventBus();
        eventBus.send("news.uk.sport", "Yay! Someone kicked a ball across a path of grass",
            ar -> {
            if (ar.succeeded()) {
                System.out.println("Received reply: " + ar.result().body());
            }
        });
    }
}
