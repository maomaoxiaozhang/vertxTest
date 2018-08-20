package io.example.test;

import io.vertx.core.AbstractVerticle;

/**
 * @author shouchang.zgq
 * @date 2018/08/15
 */
public class MyVerticle extends AbstractVerticle {
    int i = 0;

    @Override
    public void start() throws Exception {
        System.out.println("Myverticle start!");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Myverticle end!");
    }
}
