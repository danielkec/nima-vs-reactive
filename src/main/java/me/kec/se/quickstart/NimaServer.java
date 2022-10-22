package me.kec.se.quickstart;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.helidon.nima.faulttolerance.Async;
import io.helidon.nima.webclient.WebClient;
import io.helidon.nima.webclient.http1.Http1Client;
import io.helidon.nima.webserver.WebServer;

import static me.kec.se.quickstart.Main.bitcoinMining;

public class NimaServer {

    static void start(int port){
        Http1Client nimaClient = WebClient.builder()
                .baseUri("http://localhost:8080/otherService")
                .build();

        Async nimaAsync = Async.builder()
                .executor(Executors.newFixedThreadPool(2))
                .build();


        WebServer.builder()
                .port(port)
                .routing(router -> router
                        .get("/otherService", (req, res) -> {
                            res.send(Json.createObjectBuilder().add("status", "blocking_is_cool").build());
                        })


                        .get("/callOtherService", (req, res) -> {
                            String status = nimaClient.get()
                                    .request(JsonObject.class)
                                    .getString("status");

                            String upperCaseStatus = status.toUpperCase();

                            res.send(upperCaseStatus);
                        })

                        .get("/mining", (req, res) -> {
                            String result = nimaAsync
                                    .invoke(() -> bitcoinMining())
                                    // Just block virtual thread till it's done
                                    .get(10, TimeUnit.SECONDS);

                            res.send(result);
                        })

                )
                .start();
    }
}
