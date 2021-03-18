package com.example.wellneschecker;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ApiHandler {

    private String APIURL;

    ApiHandler(String place) throws IOException {
        String apiKey = "5d2000d117a89b4ac3e9ff4f4ab5c0e9";
        APIURL = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", place, apiKey);
        getData();
    }

    public void getData() throws IOException {
        String res = null;

        URL url = new URL(APIURL);
        HttpURLConnection cn = (HttpURLConnection) url.openConnection();
        cn.setRequestMethod("GET");
        InputStream in = new BufferedInputStream(cn.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;

        while ((line = br.readLine()) != null){
            sb.append(line).append(("\n"));
        }
        in.close();

        res = sb.toString();
        System.out.println(res);
        System.out.println("########DONE#############");
    }
}
