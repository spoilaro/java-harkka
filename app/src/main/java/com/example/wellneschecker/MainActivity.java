package com.example.wellneschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

    MainHandler mainHandler = new MainHandler();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        //New usage policy for the application. Needed for GET requests. Needs to be here
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        assignButtons();

    }

    public void updateWeather(View v){ // DONE
        TextView weatherTextView = findViewById(R.id.text_Weather);
        mainHandler.updateWeather(weatherTextView, context);
    }

    //Assigns buttons
    public void assignButtons(){
        buttons = new ArrayList<Button>();
        buttons.add((Button) findViewById(R.id.button_Day1));
        buttons.add((Button) findViewById(R.id.button_Day2));
        buttons.add((Button) findViewById(R.id.button_Day3));
        buttons.add((Button) findViewById(R.id.button_Day4));
        buttons.add((Button) findViewById(R.id.button_Day5));
        buttons.add((Button) findViewById(R.id.button_Day6));
        buttons.add((Button) findViewById(R.id.button_Day7));
        mainHandler.updateCalendar(buttons);

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

    //Changes the color of the button pressed.
    void onDaySelected(Button b) {
        b.setBackgroundColor(Color.parseColor("#A1A1A1"));
        previousButton = buttons.indexOf(b);

    }

    //Changes the color of the button pressed back to normal.
    void deselectPrevious(int indexOfPreviousButton) {
            buttons.get(indexOfPreviousButton).setBackgroundColor(Color.parseColor("#C6C6C6"));
    }

}