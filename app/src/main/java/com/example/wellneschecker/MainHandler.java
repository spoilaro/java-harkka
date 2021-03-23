package com.example.wellneschecker;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

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
}
