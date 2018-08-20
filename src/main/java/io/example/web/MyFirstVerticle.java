package io.example.web;

import io.vertx.core.AbstractVerticle;

/**
 * @author shouchang.zgq
 * @date 2018/08/15
 */
public class MyFirstVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                .putHeader("content-type", "text/plain")
                .end("Hello World");
        }).listen(8080);
    }
}
