package com.example.wellneschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LogInActivity extends AppCompatActivity {

    TextView usernameBox;
    TextView passwordBox;
    TextView errorBox;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        context = getApplicationContext();
    }

    public void logIn(View v){
        usernameBox = findViewById(R.id.textEdit_Username);
        passwordBox = findViewById(R.id.textEdit_Password);
        errorBox = findViewById(R.id.textView_LoginError);


        String username = usernameBox.getText().toString();
        String password = passwordBox.getText().toString();
        boolean result = checkCredentials(username, password);

        if (result == true){
            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.putExtra("username", username);
            startActivity(mainIntent);
        }
        else
            errorBox.setText("Wrong username or password!");
    }

    boolean checkCredentials(String username, String password){
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(String.format("%s.txt", username));

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
            return false;
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
            return false;
        }

        String[] readUserPassword = ret.split(" ");
        if (readUserPassword[1].equals(password)){
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