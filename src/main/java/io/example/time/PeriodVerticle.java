package io.example.time;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * @author shouchang.zgq
 * @date 2018/08/16
 */
public class PeriodVerticle extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new PeriodVerticle());
    }

    @Override
    public void start() throws Exception {
        long id = vertx.setPeriodic(1000, handler -> {
            System.out.println("timer fired");
        });
        System.out.println(id);
    }
}
