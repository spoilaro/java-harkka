package com.example.wellneschecker;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class UserProfileHandler {

    Context context;

    UserProfileHandler(){
       
    }

    public void createUserProfile() throws IOException {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser_email("rando.boomermail.com");
        userProfile.setExcercise_amount_max(3);
        userProfile.setExcercise_amount_min(1);
        userProfile.setPassword("69696969");
        userProfile.setUser_name("Pentti Boomer");

        String userJsonInfo = transformToJSON(userProfile);



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
