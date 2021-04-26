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
import java.util.regex.Pattern;

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


        String username = usernameBox.getText().toString();
        String password = passwordBox.getText().toString();
        String repassword = rePasswordBox.getText().toString();

        if (checkValidity(password, repassword)){
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(String.format("%s.txt", username), Context.MODE_PRIVATE));
                outputStreamWriter.write(String.format("%s %s", username, password));
                outputStreamWriter.close();
                changeToLogIn(v);
            }
            catch (IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
            }
        }
    }

    boolean checkValidity(String passwd, String repasswd) {
        Pattern special = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern uppercase = Pattern.compile("[A-Z ]");
        Pattern lowercase = Pattern.compile("[a-z ]");
        Pattern digits = Pattern.compile("[0-9 ]");
        TextView errorBox = findViewById(R.id.textView_RegisterError);
        boolean isValid = true;
        if (!passwd.equals(repasswd)) {
            errorBox.setText("Passwords do not match.");
            isValid = false;
        }
        if (passwd.length()<12) {
            errorBox.setText("Password must be longer than 11 characters!");
            isValid = false;
        }
        if (!special.matcher(passwd).find()) {
            errorBox.setText("Password must contain at least one special character!");
            isValid = false;
        }
        if (!uppercase.matcher(passwd).find()) {
            errorBox.setText("Password must contain at least one uppercase character!");
            isValid = false;
        }
        if (!lowercase.matcher(passwd).find()) {
            errorBox.setText("Password must contain at least one lowercase character!");
            isValid = false;
        }
        if (!digits.matcher(passwd).find()) {
            errorBox.setText("Password must contain at least one number!");
            isValid = false;
        }
        return isValid;
    }
}
