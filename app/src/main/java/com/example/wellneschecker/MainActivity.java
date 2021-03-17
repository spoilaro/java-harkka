package com.example.wellneschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Button> buttons;
    Button day1, day2, day3, day4, day5, day6, day7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons.add((Button) findViewById(R.id.button_Day1));
        buttons.add((Button) findViewById(R.id.button_Day2));
        buttons.add((Button) findViewById(R.id.button_Day3));
        buttons.add((Button) findViewById(R.id.button_Day4));
        buttons.add((Button) findViewById(R.id.button_Day5));
        buttons.add((Button) findViewById(R.id.button_Day6));
        buttons.add((Button) findViewById(R.id.button_Day7));
        updateCalendar();

    }

    void updateCalendar() {
        int i = 0;
        ArrayList<Integer> weekDates;
        DateHandler dHandler = new DateHandler();
        weekDates = dHandler.getWeekDates();
        for (Button b : buttons) {
            b.setText(weekDates.get(i));
            i++;
        }
    }

}