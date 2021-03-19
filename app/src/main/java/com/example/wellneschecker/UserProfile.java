package com.example.wellneschecker;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

public class UserProfile {
    String user_email;
    String password;
    String user_name;

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getExcercise_amount_max() {
        return excercise_amount_max;
    }

    public void setExcercise_amount_max(int excercise_amount_max) {
        this.excercise_amount_max = excercise_amount_max;
    }

    public int getExcercise_amount_min() {
        return excercise_amount_min;
    }

    public void setExcercise_amount_min(int excercise_amount_min) {
        this.excercise_amount_min = excercise_amount_min;
    }

    int excercise_amount_max;
    int excercise_amount_min;

    //TODO TIME ATTRIBUTE

    UserProfile(){
        user_email = "rando.boomermail.com";
        password = "69696969";
        user_name = "Pentti Boomer";
        excercise_amount_max = 3;
        excercise_amount_min = 1;
    }



}
