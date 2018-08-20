package io.example.cookie;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

/**
 * @author shouchang.zgq
 * @date 2018/08/16
 */
public class SessionServer extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new SessionServer());
    }

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        //增加cookies处理器，解码cookies，并将其放在context上下文中
        router.route().handler(CookieHandler.create());
        //增加session处理器，为每次用户请求，维护一个唯一的session
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

        router.route().handler(context -> {
            Session session = context.session();
            Integer count = session.get("count");
            if (count == null) {
                count = 0;
            }
            count++;
            session.put("count", count);
            context.response()
                .putHeader("content-type", "text/html")
                .end("total visit count: " + session.get("count"));
        });
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }
}
