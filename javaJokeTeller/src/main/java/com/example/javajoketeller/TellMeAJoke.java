package com.example.javajoketeller;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;


public class TellMeAJoke {

    private final static Logger logger = Logger.getLogger(Logger.class.getName());
    private static final String JOKE_URL = "http://api.icndb.com/jokes/random/";
    private static final String HTTP_REQUEST="GET";
    private static final String JSON_VALUE="value";
    private static final String JSON_JOKE="joke";

    public static String tellMeAJoke() {
        String joke=fetchJoke();
        return joke;
    }


    private static String fetchJoke() {
        String joke="";
        InputStream inputStream = null;
        try {
            URL url = new URL(JOKE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod(HTTP_REQUEST);
            connection.connect();
            inputStream= connection.getInputStream();
        } catch (IOException e) {
            logger.warning("Error getting joke from url");
            e.printStackTrace();
            return null;
        }
        String jsonResponse=getJsonResponse(inputStream);
        joke=extractJoke(jsonResponse);
        return joke;
    }

    private static String extractJoke(String jsonResponse) {
        try {
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);
            JSONObject valueObj=baseJsonResponse.getJSONObject(JSON_VALUE);
            String joke=valueObj.getString(JSON_JOKE);
            return joke;
        } catch (JSONException je) {
            logger.warning("Error getting joke from json: " + je.toString());
            return null;
        }
    }

    private static String getJsonResponse(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter("\\A");
        boolean hasInput = scanner.hasNext();
        if (hasInput) {
            return scanner.next();
        } else {
            return null;
        }
    }
}
