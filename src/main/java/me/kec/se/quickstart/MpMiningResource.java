package me.kec.se.quickstart;

import static me.kec.se.quickstart.Main.bitcoinMining;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/mining")
@RequestScoped
public class MpMiningResource {

    @GET
    public String mining() {
        return bitcoinMining();
    }
}
