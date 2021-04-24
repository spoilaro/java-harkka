package com.example.wellneschecker;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;

import java.io.IOException;
import java.util.ArrayList;

public class MainHandler {

    UserProfileHandler userProfileHandler;
    GraphBuilder graphBuilder;
    DateHandler dateHandler;
    String place;

    MainHandler(){
        graphBuilder = new GraphBuilder();
        userProfileHandler = new UserProfileHandler();
        dateHandler = new DateHandler();
        place = "Rome";
    }

    public void registerProfile(Context context, String username, String password, int moveTimesMin, int moveTimesMax) throws IOException {
        userProfileHandler.createUserProfile(context, moveTimesMax, moveTimesMin, username, password);
    }

    public void updateWeather(TextView weatherView){
        //Updating Temperature into UI.

        try {
            WeatherHandler weatherHandler = new WeatherHandler(place);
            weatherView.setText(weatherHandler.getTemperature());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getWeatherDescription(){
        String desctiption = "Description not found";

        try {
            WeatherHandler weatherHandler = new WeatherHandler(place);
            desctiption = weatherHandler.getCondition();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return desctiption;
    }

    public void updateCalendar(ArrayList<Button> buttons) {
        int i = 0;
        ArrayList<Integer> weekDates;
        DateHandler dHandler = new DateHandler();
        weekDates = dHandler.getWeekDates();
        for (Button b : buttons) {
            b.setText(weekDates.get(i).toString());
            i++;
        }
    }


    public void addToLog(Context context, int hours) throws IOException {

//        graphBuilder.createDefaultCSV(context);
        graphBuilder.addToCSV(context, String.format("%d %s", hours, dateHandler.getCurrentDateShort()));

    }

    public void readCSV(Context context, BarChart chart) throws IOException {
        graphBuilder.readCSV(context, chart);
    }
}

