package com.example.wellneschecker;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiHandler {
    private static String KEY = "5d2000d117a89b4ac3e9ff4f4ab5c0e9";

    public ApiHandler(String place) throws IOException {

        place = "Lappeenranta";
        String APIURL = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", place, KEY);

        URL url = new URL(APIURL);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try{
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            readStream(in);
        } finally {
            urlConnection.disconnect();
        }
    }

    public static void readStream(InputStream in){
        InputStreamReader isr = new InputStreamReader(in);

        Scanner s = new Scanner(isr).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        System.out.println(result);
    }
}
