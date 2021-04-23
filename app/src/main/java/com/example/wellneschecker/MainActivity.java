package com.example.wellneschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<Button> buttons;
    ArrayList<TextView> dates;
    SeekBar seekBar;

    int previousButton = 0;

    AssetManager asm;
    Context context;
    MainHandler mainHandler;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asm = getAssets();
        context = getApplicationContext();
        mainHandler = new MainHandler();

        //New usage policy for the application. Needed for GET requests. Needs to be here
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //SEEKBAR
        seekBar = findViewById(R.id.seekBar_Hours);
        image = (ImageView) findViewById(R.id.imageView_WeatherIcon);

        assignButtons();
        updateWeather();

        //change image
        //image.setImageDrawable(getDrawable(R.drawable.ic_rain_asset_sm));
        setSeekBarListener();
        try {
            setRecommendation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //Updates weather data
    public void updateWeather(){ // DONE

        TextView weatherTextView = findViewById(R.id.text_Temperature);
        mainHandler.updateWeather(weatherTextView);
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


        //Calendar weekdays
        dates = new ArrayList<TextView>();
        dates.add((TextView) findViewById(R.id.text_WeekDay1));
        dates.add((TextView) findViewById(R.id.text_WeekDay2));
        dates.add((TextView) findViewById(R.id.text_WeekDay3));
        dates.add((TextView) findViewById(R.id.text_WeekDay4));
        dates.add((TextView) findViewById(R.id.text_WeekDay5));
        dates.add((TextView) findViewById(R.id.text_WeekDay6));
        dates.add((TextView) findViewById(R.id.text_WeekDay7));
        updateCalendar();

        mainHandler.updateCalendar(buttons);


        onDaySelected(buttons.get(mainHandler.dateHandler.getWeekDates().indexOf(mainHandler.dateHandler.getCurrentDay())));
//        for(Button b : buttons) {
//            b.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    deselectPrevious(previousButton);
//                    onDaySelected(b);
//                }
//            });
//
//        }
    }


    //Updating Calendar to show right dates.
    void updateCalendar() {
        int i = 0;
        ArrayList<Integer> weekDates;
        ArrayList<String> weekDays;
        DateHandler dHandler = new DateHandler();
        weekDates = dHandler.getWeekDates();
        weekDays = dHandler.getWeekDays();
        for (i = 0; i < 7; i++) {
            buttons.get(i).setText(weekDates.get(i).toString());
            dates.get(i).setText(weekDays.get(i));
        }
    }


    //Changes the color of the button pressed.
    void onDaySelected(Button b) {
        b.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        previousButton = buttons.indexOf(b);

    }

    //Changes the color of the button pressed back to normal.
    void deselectPrevious(int indexOfPreviousButton) {
            buttons.get(indexOfPreviousButton).setBackgroundColor(Color.parseColor("#D5B7B4"));
    }

    public void changeToGraph(View v) throws IOException {
        //uncomment to get to activity graph
        setContentView(R.layout.activity_graph);
        BarChart chart = (BarChart) findViewById(R.id.chart);
        mainHandler.readCSV(context, chart);
    }

    void setSeekBarListener(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView hoursProgress = findViewById(R.id.textView_hourProgress);
                String prog = String.format("%d hours", progress);
                hoursProgress.setText(prog);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

     public void addHoursToLog(View v) {
        try {
            mainHandler.addToLog(context, seekBar.getProgress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToMain(View v) {
        setContentView(R.layout.activity_main);

        //SEEKBAR
        seekBar = findViewById(R.id.seekBar_Hours);

        assignButtons();
        updateWeather();

        setSeekBarListener();
    }

    public void setRecommendation() throws IOException {
        TextView recommendation = (TextView) findViewById(R.id.text_DayInfoHeader);
        WeatherHandler weatherHandler = new WeatherHandler("Lappeenranta");
        String condition = weatherHandler.getCondition();
        RecommendationHandler recommendationHandler = new RecommendationHandler(asm, condition);
        recommendation.setText(recommendationHandler.getRecommendation());
        System.out.println(weatherHandler.getCondition());

    }

}