package com.example.wellneschecker;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;

import java.io.IOException;
import java.util.ArrayList;

class MainHandler {

    GraphBuilder graphBuilder;
    DateHandler dateHandler;
    String place;

    MainHandler(String placename){
        graphBuilder = new GraphBuilder();
        dateHandler = new DateHandler();
        place = placename;
    }

    //Updating Temperature into UI.
    void updateWeather(TextView weatherView){

        try {
            WeatherHandler weatherHandler = new WeatherHandler(place);
            weatherView.setText(weatherHandler.getTemperature());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Gets weather condition/description
    String getWeatherDescription(){
        String desctiption = "Description not found";

        try {
            WeatherHandler weatherHandler = new WeatherHandler(place);
            desctiption = weatherHandler.getCondition();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return desctiption;
    }

    //Updates dates to the calendar
    void updateCalendar(ArrayList<Button> buttons) {
        int i = 0;
        ArrayList<Integer> weekDates;
        DateHandler dHandler = new DateHandler();
        weekDates = dHandler.getWeekDates();
        for (Button b : buttons) {
            b.setText(weekDates.get(i).toString());
            i++;
        }
    }


    //Adds an entry to the log
    void addToLog(Context context, int hours) throws IOException {

//        graphBuilder.createDefaultCSV(context);
        graphBuilder.addToCSV(context, String.format("%d %s", hours, dateHandler.getCurrentDateShort()));

    }

    //Reads data from log to graph
    void readCSV(Context context, BarChart chart) throws IOException {
        graphBuilder.readCSV(context, chart);
    }
}

