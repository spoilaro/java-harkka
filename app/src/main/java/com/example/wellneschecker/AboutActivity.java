package com.example.wellneschecker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Intent usernameIntent = getIntent();
        username = usernameIntent.getStringExtra("username");
    }

    public void switchToMain(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("username", username);
        startActivity(mainIntent);
    }

    public void switchToSettings(View v) {
        Intent settingsIntent = new Intent(this, SettingsActivityS.class);
        settingsIntent.putExtra("username", username);
        startActivity(settingsIntent);
    }
}
