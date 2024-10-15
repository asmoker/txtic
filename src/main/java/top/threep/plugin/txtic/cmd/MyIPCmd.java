package top.threep.plugin.txtic.cmd;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class MyIPCmd implements Cmd {

    public MyIPCmd() {
    }

    private String getMyIP() {
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder()
                            .uri(URI.create("https://ip.threep.top"))
                            .timeout(Duration.ofSeconds(3))
                            .GET()
                            .build(),
                    HttpResponse.BodyHandlers.ofString()
            );
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String run(String text) {
        return getMyIP();
    }
}
