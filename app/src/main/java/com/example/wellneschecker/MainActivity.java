package com.example.wellneschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<Button> buttons;
    int previousButton = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //New usage policy for the application. Needed for GET requests.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Updating Temperature into UI.
        TextView weatherTextView = findViewById(R.id.text_Weather);
        try {
            WeatherHandler weatherHandler = new WeatherHandler("Lappeenranta");
            weatherTextView.setText(weatherHandler.temperature);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //User profile handling
        UserProfileHandler usrh = new UserProfileHandler();
        try {
            usrh.createUserProfile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Calendar Buttons
        buttons = new ArrayList<Button>();
        buttons.add((Button) findViewById(R.id.button_Day1));
        buttons.add((Button) findViewById(R.id.button_Day2));
        buttons.add((Button) findViewById(R.id.button_Day3));
        buttons.add((Button) findViewById(R.id.button_Day4));
        buttons.add((Button) findViewById(R.id.button_Day5));
        buttons.add((Button) findViewById(R.id.button_Day6));
        buttons.add((Button) findViewById(R.id.button_Day7));
        updateCalendar();
        onDaySelected(buttons.get(0));
        for(Button b : buttons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deselectPrevious(previousButton);
                    onDaySelected(b);
                }
            });

        }
    }

    //Updating Calendar to show right dates.
    void updateCalendar() {
        int i = 0;
        ArrayList<Integer> weekDates;
        DateHandler dHandler = new DateHandler();
        weekDates = dHandler.getWeekDates();
        for (Button b : buttons) {
            b.setText(weekDates.get(i).toString());
            i++;
        }
    }

    //Changes the color of the button pressed.
    void onDaySelected(Button b) {
        b.setBackgroundColor(Color.parseColor("#A1A1A1"));
        previousButton = buttons.indexOf(b);

    }
    void deselectPrevious(int indexOfPreviousButton) {
            buttons.get(indexOfPreviousButton).setBackgroundColor(Color.parseColor("#C6C6C6"));
    }

}