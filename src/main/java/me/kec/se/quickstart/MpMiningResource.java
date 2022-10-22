package me.kec.se.quickstart;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.faulttolerance.Asynchronous;

import static me.kec.se.quickstart.Main.bitcoinMining;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/mining")
@RequestScoped
public class MpMiningResource {

    @GET
    @Asynchronous
    public CompletionStage<String> mining() {
        String result = bitcoinMining();
        return CompletableFuture.completedStage(result);
    }
}
