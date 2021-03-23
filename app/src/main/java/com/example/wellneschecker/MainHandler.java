package com.example.wellneschecker;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainHandler {


    public void updateWeather(TextView weatherView, Context context){
        //Updating Temperature into UI.

        try {
            WeatherHandler weatherHandler = new WeatherHandler("Lappeenranta");
            weatherView.setText(weatherHandler.temperature);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //User profile handling
        UserProfileHandler usrh = new UserProfileHandler();
        try {
            usrh.createUserProfile(context);
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
}
