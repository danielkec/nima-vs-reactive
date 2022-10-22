package me.kec.se.quickstart;

import io.helidon.logging.common.LogConfig;
import io.helidon.microprofile.server.Server;

public final class Main {

    public static void main(final String[] args) {
        LogConfig.configureRuntime();

        // Helidon Nima
        NimaServer.start(8080);
        // Helidon SE
        SeServer.start(8081);
        // Helidon MP
        Server.builder().port(8082).build().start();
    }

    static String bitcoinMining() {
        long start = System.currentTimeMillis();
        long end = start + (5 * 1000);
        while (true) {
            // CPU burn!
            if (System.currentTimeMillis() > end) {
                break;
            }
        }
        return "Got no bitcoins, but burnt lot of CPU time on " + Thread.currentThread() + "!";
    }
}
