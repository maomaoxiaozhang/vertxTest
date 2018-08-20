package io.example.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

/**
 * @author shouchang.zgq
 * @date 2018/08/16
 */
public class EventBusConsumer extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new EventBusConsumer());
    }

    @Override
    public void start() {
        eventbus();
    }

    private void eventbus() {
        EventBus eventBus = vertx.eventBus();

        MessageConsumer<String> consumer = eventBus.consumer("news.uk.sport");
        consumer.handler(message -> {
            System.out.println("I have received a message: " + message);
            message.reply("interesting!");
        });

        consumer.completionHandler(res -> {
            if (res.succeeded()) {
                System.out.println("The handler registration has reached all nodes");
            }else {
                System.out.println("registration failed");
            }
        });

        //eventBus.send("news.uk.sport", "Yay! Someone kicked a ball across a path of grass",
        //    ar -> {
        //        if (ar.succeeded()) {
        //            System.out.println("Received reply: " + ar.result().body());
        //        }
        //    });
    }
}
