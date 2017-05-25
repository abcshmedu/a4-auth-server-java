package edu.hm.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Provides some basic http-functionality.
 */
public class Http {

    public Http() {
    }

    public HttpResult get(String stringUrl, Map<String, String> headerFields) {
        HttpResult result = null;
        try {
            URL url = new URL(stringUrl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            for (Map.Entry<String, String> headerEntry : headerFields.entrySet()) {
                con.setRequestProperty(headerEntry.getKey(), headerEntry.getValue());
            }

            int statusCode = con.getResponseCode();
            String resultMessage = con.getResponseMessage();
            result = new HttpResult(statusCode, resultMessage, "");
        } catch (IOException ignored) {
        }

        return result;
    }

    public HttpResult postJson(String stringUrl, Map<String, String> headerFields, Object payload) {
        HttpResult result = null;
        try {
            URL url = new URL(stringUrl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            for (Map.Entry<String, String> headerEntry : headerFields.entrySet()) {
                con.setRequestProperty(headerEntry.getKey(), headerEntry.getValue());
            }

            write(con, payload);

            int statusCode = con.getResponseCode();
            String resultMessage = con.getResponseMessage();
            String resultContent = read(con);
            result = new HttpResult(statusCode, resultMessage, resultContent);
        } catch (IOException ignored) {
        }

        return result;
    }

    private String read(HttpURLConnection con) {
        String result;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder sb = new StringBuilder();

            String buffer;
            while ((buffer = br.readLine()) != null) {
                sb.append(buffer);
            }

            result = sb.toString();
        } catch (IOException ex) {
            result = "";
        }

        return result;
    }

    private boolean write(HttpURLConnection con, Object payload) {
        try {
            OutputStream os = con.getOutputStream();
            os.write(Json.serializeObject(payload).getBytes("UTF-8"));
            os.close();
        } catch (IOException ex) {
            return false;
        }

        return true;
    }
}
