package io.example.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * @author shouchang.zgq
 * @date 2018/08/16
 */
public class RestfulVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        System.out.println("start!");
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.get("/get/:param1/:param2").handler(this::handleGet);
        router.route("/assets/*").handler(StaticHandler.create("assets"));
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

    private void handleGet(RoutingContext routingContext) {
        String param1 = routingContext.request().getParam("param1");
        String param2 = routingContext.request().getParam("param2");
        if (isBlank(param1) || isBlank(param2)) {
            routingContext.response().setStatusCode(400).end();
        }
        JsonObject obj = new JsonObject();
        obj.put("method", "get").put("param1", param1).put("param2", param2);
        routingContext.response().putHeader("content-type", "application/json")
            .end(obj.encodePrettily());
    }

    private boolean isBlank(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }
}
