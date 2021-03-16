package com.example.wellneschecker;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;


class DateHandler{

    static int currentDay;
    static ArrayList<Integer> weekDays;

    DateHandler(){     //TODO Make it return right value when month changes!!

        //Declarations and initializing
        Date currentTime = new Date();
        Calendar calendar = Calendar.getInstance();
        weekDays = new ArrayList<Integer>();

        //Current time
        currentTime = calendar.getTime();
        calendar.setTime(currentTime);
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        //Parse week dates
        calendar.setTime(currentTime);
        //calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));

        //Add dates to array
        int mondayDate = calendar.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_WEEK) + 2;

        for (int i=0; i<7; i++){
            weekDays.add(mondayDate);
            mondayDate += 1;
        }
    }

    public int getCurrentDay(){
        return currentDay;
    }

    public ArrayList<Integer> getWeekDates(){
        return weekDays;
    }


}
