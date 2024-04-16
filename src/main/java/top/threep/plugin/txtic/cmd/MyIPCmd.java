package top.threep.plugin.txtic.cmd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyIPCmd implements Cmd {

    public MyIPCmd() {
    }

    private String getMyIP() {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL("https://ip.threep.top");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int timeout = 3000; // in ms
            conn.setConnectTimeout(timeout);
            conn.setReadTimeout(timeout);
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            br.close();
        } catch (Exception e) {
            return "";
        }
        return result.toString().strip();

    }

    @Override
    public String run(String text) {
        return getMyIP();
    }
}
