package com.example.wellneschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsActivityS extends AppCompatActivity {

    public static final String SHARED_PREF = "SHARED_PREF";
    public static final String PREF_KEY = "place";

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_s);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String unit = sharedPreferences.getString("unit", "°C");
        Switch unitSwitch = findViewById(R.id.switch1);

        Intent usernameIntent = getIntent();
        username = usernameIntent.getStringExtra("username");

        String place = sharedPreferences.getString("place", "Lappeenranta");
        TextView changePlace = findViewById(R.id.enter_place);
        changePlace.setText(place);

        if (unit.equals("°F")){
            unitSwitch.setChecked(true);
        }
        unitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                operateUnitSwitch();
            }
        });
    }

    //Changes the view to home view
    public void changeToHome(View v){
        Intent homeIntent = new Intent(this, MainActivity.class);
        homeIntent.putExtra("username", username);
        startActivity(homeIntent);
    }

    public void changeToAbout(View v){
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        aboutIntent.putExtra("username", username);
        startActivity(aboutIntent);
    }

    public void changeToLogin(View v){
        Intent loginIntent = new Intent(this,LogInActivity.class);
        startActivity(loginIntent);
    }

    //Changes the place to the preferences
    public void changePlace(View v){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        TextView place_input = findViewById(R.id.enter_place);
        String placeName = place_input.getText().toString();
        if(placeName.equals("")){
            placeName = sharedPreferences.getString(PREF_KEY, "Lappeenranta");
        }
        editor.putString(PREF_KEY, placeName);
        editor.commit();
    }

    public void operateUnitSwitch(){
        String unit;
        Switch unitSwitch = findViewById(R.id.switch1);

        if (unitSwitch.isChecked() == false){
            unit = "°C";
        } else {
            unit ="°F";
        }

        changeUnit(unit);
    }

    void changeUnit(String unit){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("unit", unit);
        editor.commit();
    }
}