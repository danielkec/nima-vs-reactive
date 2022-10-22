package me.kec.se.quickstart;

import jakarta.enterprise.context.RequestScoped;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

@Path("/callOtherService")
@RequestScoped
public class MpCallOtherServiceResource {

    private WebTarget webTarget = ClientBuilder.newClient()
            .target("http://localhost:8080/otherService");

    @GET
    public String callOtherService() {
        JsonObject jo = webTarget.request()
                .buildGet()
                .invoke(JsonObject.class);

        return jo.getString("status").toUpperCase();
    }
}
