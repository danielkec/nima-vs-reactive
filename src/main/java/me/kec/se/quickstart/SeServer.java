package me.kec.se.quickstart;

import jakarta.json.JsonObject;

import java.util.concurrent.Executors;

import io.helidon.reactive.faulttolerance.Async;
import io.helidon.reactive.media.jsonp.JsonpSupport;
import io.helidon.reactive.webclient.WebClient;
import io.helidon.reactive.webserver.WebServer;

import static me.kec.se.quickstart.Main.bitcoinMining;

public class SeServer {
    static void start(int port){

        WebClient seClient = WebClient.builder()
                .addMediaSupport(JsonpSupport.create())
                .baseUri("http://localhost:8080/otherService")
                .build();

        Async reactiveAsync = Async.builder()
                .executor(Executors.newFixedThreadPool(2))
                .build();

        WebServer.builder()
                .port(port)
                .routing(router -> router

                        .get("/callOtherService", (req, res) -> {
                            seClient.get()
                                    .request(JsonObject.class)
                                    .map(jo -> jo.getString("status"))
                                    .map(String::toUpperCase)
                                    .onError(res::send)
                                    .forSingle(s -> {
                                        res.send(s);
                                    });
                        })

                        .get("/mining", (req, res) -> {
                            reactiveAsync
                                    .invoke(() -> bitcoinMining())
                                    .onError(res::send)
                                    .forSingle(res::send);
                        })

                )

                .addMediaSupport(JsonpSupport.create())
                .build()
                .start();
    }
}
