package com.example.wellneschecker;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class UserProfileHandler {

    UserProfileHandler(){
    }

    public void createUserProfile(Context context, int max, int min, String username, String password) throws IOException {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser_email("rando.boomermail.com");
        userProfile.setExcercise_amount_max(max);
        userProfile.setExcercise_amount_min(min);
        userProfile.setPassword(password);
        userProfile.setUser_name(username);

        String userJsonInfo = transformToJSON(userProfile);

        //THIS METHOD OF WRITING IS WORKING
        File file = new File(context.getFilesDir(), String.format("%s.json", username));
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userJsonInfo);
        bufferedWriter.close();
        System.out.println("#########DONE##########");
    }

    public String transformToJSON(UserProfile user) throws IOException {
        ObjectMapper mp = new ObjectMapper();
        String jsdonData = mp.writeValueAsString(user);
        System.out.println(jsdonData);
        return  jsdonData;
    }

    //TODO READ JSON STRING
}
