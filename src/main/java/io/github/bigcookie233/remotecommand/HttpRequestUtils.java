package io.github.bigcookie233.remotecommand;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestUtils {
    public JsonObject doPost(String url, JsonObject json) {
        JsonObject response = null;
        HttpURLConnection conn = null;
        OutputStream out = null;
        BufferedReader br = null;

        try {
            URL apiUrl = new URL(url);
            conn = (HttpURLConnection) apiUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();

            out = conn.getOutputStream();
            out.write(json.toString().getBytes());
            out.flush();

            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            response = JsonParser.parseReader(br).getAsJsonObject();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            // 关闭连接
            try {
                if (br != null) {
                    br.close();
                }
                if (out != null) {
                    out.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return response;
    }
}
