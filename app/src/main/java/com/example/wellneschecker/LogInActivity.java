package com.example.wellneschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {

    TextView usernameBox;
    TextView passwordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void logIn(View v){
        usernameBox = findViewById(R.id.textEdit_Username);
        passwordBox = findViewById(R.id.textEdit_Password);


        String username = usernameBox.getText().toString();
        String password = passwordBox.getText().toString();
        boolean result = checkCredentials(username, password);

        if (result == true){
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        }
    }

    boolean checkCredentials(String username, String password){
        if (username.equals("admin") && password.equals("admin")){
            return true;
        } else {
            return false;
        }
    }

    public void changeToCreateUser(View v){
        Intent createUserIntent = new Intent(this, CreateProfile.class);
        startActivity(createUserIntent);
    }

}