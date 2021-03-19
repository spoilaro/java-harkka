package com.example.wellneschecker;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class UserProfileHandler {

    UserProfileHandler(){
    }

    public void createUserProfile(Context context) throws IOException {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser_email("rando.boomermail.com");
        userProfile.setExcercise_amount_max(3);
        userProfile.setExcercise_amount_min(1);
        userProfile.setPassword("69696969");
        userProfile.setUser_name("Pentti Boomer");

        String userJsonInfo = transformToJSON(userProfile);

        File file = new File(context.getFilesDir(), "rando.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write("hello\n");
        //TODO file is created but it is empty!!!!!!!!!!
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
