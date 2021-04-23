package com.example.wellneschecker;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Float.parseFloat;

public class WeatherHandler {

    private String APIURL;
    public String temperature;
    public String condition;

    WeatherHandler(String place) throws IOException {
        String apiKey = "5d2000d117a89b4ac3e9ff4f4ab5c0e9";
        APIURL = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s", place, apiKey);
        temperature = parseData(getData());
        condition = parseCondition(getData());
    }

    //Makes the GET request to OpenWeatherMap.org and returns a json string.
    public String getData() throws IOException {
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
        return  res;

    }

    //Parses a json string to different temperatures.
    public String parseData(String raw) throws IOException {
        ObjectMapper mp = new ObjectMapper();
        Map<String, Object> allWeather = mp.readValue(raw, Map.class);

        String tmpData = allWeather.get("main").toString();
        String[] tmpArray = tmpData.split("[,{}]"); //1=base temperature 2=feels like temperature
        return tmpArray[1]; //Returns base temperature.
    }

    public String parseCondition(String raw) throws IOException {
        ObjectMapper mp = new ObjectMapper();
        Map<String, Object> allWeather = mp.readValue(raw, Map.class);

        String tmpData = allWeather.get("weather").toString();
        System.out.println(tmpData);
        String[] tmpArray = tmpData.split("[,{}]"); //1=base temperature 2=feels like temperature
        System.out.println(tmpArray[2]);
        return tmpArray[2]; //Returns base temperature.
    }

    public String getCondition() {
        String cond;
        cond = condition.split("=")[1];
        return cond;
    }

    public String getTemperature() {
        String temp;
        String[] tempArray;
        tempArray = temperature.split("=");
        int tempInt = (int) Float.parseFloat(tempArray[1]);
        temp = String.format("%d", tempInt);
        temp = temp + "°C";
        return temp;
    }
}
