package com.example.wellneschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivityS extends AppCompatActivity {

    Button addMinMax;
    SeekBar max;
    SeekBar min;
    int maxHours;
    int minHours;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_s);

        addMinMax = findViewById(R.id.minmaxbutton);

        setMaxListener();
        setMinListener();
        setButtonListener();

        context = getApplicationContext();
    }

    public void changeToHome(View v){
        Intent homeIntent = new Intent(this, MainActivity.class);
        startActivity(homeIntent);
    }

    public void getMinMax(){
        System.out.println(String.format("max is %d and min is %d", maxHours, minHours));
    }

    void setMaxListener(){
        max = findViewById(R.id.seekBarMax);
        max.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxHours = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    void setMinListener(){
        min = findViewById(R.id.seekBarMin);
        min.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minHours = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    void setButtonListener(){
        addMinMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMinMax();
            }
        });
    }

}