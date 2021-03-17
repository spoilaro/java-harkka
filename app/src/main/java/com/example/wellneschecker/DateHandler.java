package com.example.wellneschecker;

import java.util.Calendar;
import java.util.ArrayList;


class DateHandler{

    static int currentDay;
    static ArrayList<Integer> weekDays;

    DateHandler(){

        //Declarations and initializing
        Calendar calendar = Calendar.getInstance();
        weekDays = new ArrayList<Integer>();

        //Current time
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        // Set the time to this week's monday
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        //Add dates to array
        for (int i=0; i<7; i++){
            weekDays.add(calendar.get(Calendar.DAY_OF_MONTH));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    public int getCurrentDay(){
        return currentDay;
    }

    public ArrayList<Integer> getWeekDates(){
        return weekDays;
    }

}
