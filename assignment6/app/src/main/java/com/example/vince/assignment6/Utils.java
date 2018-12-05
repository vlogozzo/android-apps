package com.example.vince.assignment6;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public final class Utils {

    public static final String LOG_TAG = "inspect";//Utils.class.getSimpleName();


    public static List<String> fetchEarthquakeData(String requestUrl) {
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(createUrl(requestUrl));
        } catch (IOException e) {
            Log.d(LOG_TAG, "Error closing input stream");
        }
        return extractFeatureFromJson(jsonResponse);
    }

    private static URL createUrl(String stringUrl) {
        try {
            return new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.d(LOG_TAG, "Error with creating URL ", e);
            return null;
        }
    }

    private static String readAll(BufferedReader rd) throws IOException {
        StringBuffer sb = new StringBuffer();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        try {
            InputStream is = new URL(url.toString()).openStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(read);
            JSONObject json = new JSONObject(jsonText);
            read.close();
            is.close();
            return json.toString();
        } catch (Exception e) {
            Log.d(LOG_TAG, "Problem " + e.getMessage());
            throw new IOException("error");
        }
    }

    private static List<String> extractFeatureFromJson(String earthquakeJSON) {
        if (TextUtils.isEmpty(earthquakeJSON)) {
            Log.d("inspect", "extractFeatureFromJson: json is empty");
            return null;
        }
        List<String> quakeList = new ArrayList<>();
        try {
            JSONArray earthquakeArray = new JSONObject(earthquakeJSON).getJSONArray("features");
            for (int i = 0; i < earthquakeArray.length(); i++) {
                JSONObject properties = earthquakeArray.getJSONObject(i).getJSONObject("properties");
                String title = properties.getString("title");
                String time = properties.getString("time");
                JSONObject geometry = earthquakeArray.getJSONObject(i).getJSONObject("geometry");
                JSONArray coords = geometry.getJSONArray("coordinates");
                String mag = properties.getString("mag");
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append(title)
                        .append("@@");
                stringBuilder.append(time)
                        .append("@@");
                stringBuilder.append(coords.get(0))
                        .append("@@");
                stringBuilder.append(coords.get(1))
                        .append("@@");
                stringBuilder.append(mag);

                quakeList.add(stringBuilder.toString());
            }
        } catch (JSONException e) {
            Log.d(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return quakeList;
    }
}