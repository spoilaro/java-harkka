package com.example.wellneschecker;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainHandler {

    UserProfileHandler userProfileHandler;
    GraphBuilder graphBuilder;

    MainHandler(){
        graphBuilder = new GraphBuilder();
        userProfileHandler = new UserProfileHandler();
    }

    public void registerProfile(Context context, String username, String password, int moveTimesMin, int moveTimesMax) throws IOException {
        userProfileHandler.createUserProfile(context, moveTimesMax, moveTimesMin, username, password);
    }

    public void updateWeather(TextView weatherView){
        //Updating Temperature into UI.

        try {
            WeatherHandler weatherHandler = new WeatherHandler("Lappeenranta");
            weatherView.setText(weatherHandler.getTemperature());
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public void createCSV(Context context) throws IOException {
        graphBuilder.createDefaultCSV(context);
    }
}
