package com.example.wellneschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsActivityS extends AppCompatActivity {

    public static final String SHARED_PREF = "SHARED_PREF";
    public static final String PREF_KEY = "place";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_s);
    }

    //Changes the view to home view
    public void changeToHome(View v){
        Intent homeIntent = new Intent(this, MainActivity.class);
        startActivity(homeIntent);
    }

    public void changeToAbout(View v){
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        startActivity(aboutIntent);
    }

    public void changeToLogin(View v){
        Intent loginIntent = new Intent(this,LogInActivity.class);
        startActivity(loginIntent);
    }

    public void changePlace(View v){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        TextView place_input = findViewById(R.id.enter_place);
        editor.putString(PREF_KEY, place_input.getText().toString());
        editor.commit();
    }
}