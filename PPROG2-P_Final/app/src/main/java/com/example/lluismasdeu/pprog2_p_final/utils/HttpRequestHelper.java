package com.example.lluismasdeu.pprog2_p_final.utils;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eloy on 09-05-2017.
 */

public class HttpRequestHelper {
    private static HttpRequestHelper instance = null;
    private final int DEFAULT_TIMEOUT = 500;

    private HttpRequestHelper(){}

    public static HttpRequestHelper getInstance() {
        if (instance == null) {
            instance = new HttpRequestHelper();
        }

        return instance;
    }

    public JSONObject doHttpRequest(String url, String method) {
        HttpURLConnection c = null;
        JSONObject jsonObject = new JSONObject();
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod(method);
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(DEFAULT_TIMEOUT);
            c.setReadTimeout(DEFAULT_TIMEOUT);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    br.close();

                    jsonObject = new JSONObject(sb.toString());
            }

        } catch (Exception ex) {
            Log.e(getClass().getName(), "Exception", ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Log.e(getClass().getName(), "Exception", ex);
                }
            }
        }

        return jsonObject;
    }

}
