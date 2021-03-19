package com.example.wellneschecker;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UserProfileHandler {
    UserProfile currentUserProfile;

    UserProfileHandler(){
        currentUserProfile = new UserProfile();
    }

    //TODO CREATE JAVA OBJECT UserProfile

    public String transformToJSON() throws IOException {
        ObjectMapper mp = new ObjectMapper();
        String jsdonData = mp.writeValueAsString(currentUserProfile);
        System.out.println(jsdonData);
        return  jsdonData;
    }

    //TODO READ JSON STRING
}
