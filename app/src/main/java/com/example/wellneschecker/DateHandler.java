package com.example.wellneschecker;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

class DateHandler{

    static int currentDay;
    static ArrayList<Integer> weekDates;
    static ArrayList<String> weekDays;
    String shortDate;
    String weekDateName;

    DateHandler(){     //TODO Make it return right value when month changes!!

        //Declarations and initializing
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        SimpleDateFormat shortDateFormat = new SimpleDateFormat("d.M");
        weekDates = new ArrayList<Integer>();
        weekDays = new ArrayList<String>();

        //Week date name
        String[] dayNames = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        weekDateName = dayNames[calendar.get(Calendar.DAY_OF_WEEK) - 1];

        //Short date
        shortDate = shortDateFormat.format(calendar.getTime());

        //Current time
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setFirstDayOfWeek(2);
        System.out.println(calendar.getFirstDayOfWeek());

        // Set the time to this week's monday
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        //Add dates to array
        for (int i=0; i<7; i++){
            weekDates.add(calendar.get(Calendar.DAY_OF_MONTH));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        for (int i = 0; i < 7; i++) {
            String sh = sdf.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            weekDays.add(sh);
        }

    }

    public int getCurrentDay(){
        return currentDay;
    }

    public String getCurrentDateShort() {
        return shortDate;
    }

    public ArrayList<Integer> getWeekDates(){
        return weekDates;
    }

    public ArrayList<String> getWeekDays() {
        return weekDays;
    }

    public String getWeekDateName(){
        return  weekDateName;
    }
}