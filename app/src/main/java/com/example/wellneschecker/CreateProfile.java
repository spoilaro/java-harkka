package com.example.wellneschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;

public class CreateProfile extends AppCompatActivity {

    TextView usernameBox;
    TextView passwordBox;
    TextView rePasswordBox;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        context = getApplicationContext();
    }

    public void changeToLogIn(View v){
        Intent loginIntent = new Intent(this, LogInActivity.class);
        startActivity(loginIntent);
    }

    public void addUser(View v) {
        usernameBox = findViewById(R.id.textEdit_CreateUsername);
        passwordBox = findViewById(R.id.textEdit_CreatePassword);
        rePasswordBox = findViewById(R.id.textEdit_ConfirmPassword);
        TextView errorBox = findViewById(R.id.errorView);

        String username = usernameBox.getText().toString();
        String password = passwordBox.getText().toString();
        String repassword = rePasswordBox.getText().toString();

        if (password.length()<=12){
            errorBox.setText("Password must be longer than 11 characters"); //TODO Need to implement good password practices. 1 special ch and 1 nnum
        } else{
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(String.format("%s.txt", username), Context.MODE_PRIVATE));
                outputStreamWriter.write(String.format("%s %s", username, password));
                outputStreamWriter.close();
            }
            catch (IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
            }
        }


    }
}
