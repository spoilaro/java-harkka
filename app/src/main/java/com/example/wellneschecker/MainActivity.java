package com.example.wellneschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
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
    BarChart chart;
    SharedPreferences sharedPref;
    String place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        place = "Lappeenranta";

        asm = getAssets();
        context = getApplicationContext();
        mainHandler = new MainHandler(place);

        //New usage policy for the application. Needed for GET requests. Needs to be here
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //SEEKBAR
        seekBar = findViewById(R.id.seekBar_Hours);
        image = (ImageView) findViewById(R.id.imageView_WeatherIcon);
        chart = (BarChart) findViewById(R.id.chart2);

        assignButtons();
        updateWeather();
        updateDate();
        updateDescription();





        try {
            loadGraph();
            changeIcon();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    void updateWeather(){ // DONE

        TextView weatherTextView = findViewById(R.id.text_Temperature);
        mainHandler.updateWeather(weatherTextView);
    }

    //Assigns buttons
    void assignButtons(){
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

    //Loads the graph
    void loadGraph() throws IOException {

        mainHandler.readCSV(context, chart);

    }

    //Changes the view to settings view
    public void changeToSettings(View v){
        Intent settingsIntent = new Intent(this, SettingsActivityS.class);
        startActivity(settingsIntent);
    }

    //Creates the listener for seekbar so app can add hours to the graph
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

    //Adds hours to the log
     public void addHoursToLog(View v) {
        try {
            mainHandler.addToLog(context, seekBar.getProgress());
            mainHandler.readCSV(context, chart);
            chart.invalidate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Changes the view to home view
    public void changeToMain(View v) {
        setContentView(R.layout.activity_main);

        //SEEKBAR
        seekBar = findViewById(R.id.seekBar_Hours);

        assignButtons();
        updateWeather();

        setSeekBarListener();
    }

    //Sets the recommendation text
    public void setRecommendation() throws IOException {
        TextView recommendation = (TextView) findViewById(R.id.text_DayInfoHeader);
        WeatherHandler weatherHandler = new WeatherHandler(place);
        String condition = weatherHandler.getCondition();
        RecommendationHandler recommendationHandler = new RecommendationHandler(asm, condition);
        recommendation.setText(recommendationHandler.getRecommendation());
        System.out.println(weatherHandler.getCondition());

    }

    //Changes the weather icon
    public void changeIcon() throws IOException {
        WeatherHandler weatherHandler = new WeatherHandler(place);
        switch (weatherHandler.getCondition()) {
            case "Thunderstorm":
                image.setImageDrawable(getDrawable(R.drawable.ic_thunder_asset_sm));
                break;
            case "Drizzle":
                image.setImageDrawable(getDrawable(R.drawable.ic_rain_asset_sm));
                break;
            case "Rain":
                image.setImageDrawable(getDrawable(R.drawable.ic_rain_asset_sm));
                break;
            case "Snow":
                image.setImageDrawable(getDrawable(R.drawable.ic_snow_asset_sm));
                break;
            case "Clear":
                image.setImageDrawable(getDrawable(R.drawable.ic_sun_asset_sm));
                break;
            case "Clouds":
                image.setImageDrawable(getDrawable(R.drawable.ic_cloud_asset_sm));
                break;
        }
    }

    //Updates topbar's date
    void updateDate(){
        TextView weatherDate = findViewById(R.id.text_WeatherDate);
        String shortDate = mainHandler.dateHandler.getCurrentDateShort();
        String datename = mainHandler.dateHandler.getWeekDateName();
        weatherDate.setText(String.format("%s %s", shortDate, datename));
    }

    //Updates top bar's weather condition/description
    void updateDescription(){
        TextView weatherDescription = findViewById(R.id.text_WeatherCondition);
        String description = mainHandler.getWeatherDescription();
        weatherDescription.setText(description);
        System.out.println(String.format("desc is  %s ##############", description));

    }

}