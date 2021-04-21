package com.example.wellneschecker;

import android.content.Context;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GraphBuilder {

    DateHandler dateHandler;


    GraphBuilder() {
        dateHandler = new DateHandler();

    }

    ;

    public void readMove() {

    }

    ;

    public void addToCSV(Context context, String data) { //method that shouldn't allow to add more than 7 lines to the log file
        int x = 0;
        File tempFile = new File(context.getFilesDir(), String.format("temp.txt"));
        File file = new File(context.getFilesDir(), String.format("move.txt"));
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            //get the number of lines in the file
            while (br.readLine() != null) {
                System.out.println("Toimii");
                x++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //if the number of lines is greater than 8, remove the first line and then copy every line after that and add the new data
        if (x > 7) {
            try {
                Scanner scanFile = new Scanner(file);
                System.out.println("#############111##############");

                BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));
                scanFile.nextLine(); //skip the first line
                while (scanFile.hasNextLine()) { //now go through the lines one at a time
                    String next = scanFile.nextLine(); //get the next line and save it to a string
                    out.write(next + "\n");
                }
                out.write(data + "\n");
                scanFile.close();
                out.close();
                boolean success = tempFile.renameTo(file);
                if (!success)
                    System.out.println("fail");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (x == 0) { //if the number of lines is 0, just add the new data
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write(data + "\n");
                out.close();
                System.out.println("#############222##############");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { // if the number of lines is something between 0 and 8, copy every line and add the new data
            try {
                Scanner scanFile = new Scanner(file);
                BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));
                while (scanFile.hasNextLine()) {
                    String next = scanFile.nextLine();
                    System.out.println("#########" + next);
                    out.write(next+"\n");
                }
                out.write(data+"\n");
                out.close();
                boolean success = tempFile.renameTo(file);
                if (!success)
                    System.out.println("fail");
                System.out.println("#############333##############");
                scanFile.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createDefaultCSV(Context context) throws IOException {

        String demodata = "" +
                "1 1.12.\n" +
                "3 2.12.\n" +
                "5 3.12.\n" +
                "2 4.12.\n" +
                "7 5.12.\n" +
                "3 6.12.\n" +
                "1 7.12.\n";

        File file = new File(context.getFilesDir(), String.format("move.txt"));
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(demodata);
        bufferedWriter.close();
    }

    void readCSV(Context context, BarChart chart) {
        int x = 0;
        ArrayList<String> days = new ArrayList<>();
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        String line;
        File file = new File(context.getFilesDir(), String.format("move.txt"));
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            //this loop creates a list of entries that we need to create the graph
            while ((line = br.readLine()) != null) {
                entries.add(new BarEntry(x, Integer.parseInt(line.split(" ")[0])));
                days.add(line.split(" ")[1]);
                x++;
            }
            BarDataSet dataSet = new BarDataSet(entries, "Tunnit"); //creates a new data set required to make the data below
            BarData data = new BarData(dataSet); //this data is required to draw the actual graph
            dataSet.setValueTextSize(22);
            dataSet.setGradientColor(0xFFFA9284, 0xFF384E78);
            chart.setData(data);
            for (String s : days) {
                System.out.println("####");
                System.out.println(s);
                System.out.println("####");
            }
            //here we create a new value formatter that lets us set the x axis values to represent the dates
            ValueFormatter xFormatter = new ValueFormatter() {
                @Override
                public String getAxisLabel(float value, AxisBase axis) {
                    return days.get((int) value);
                }
            };

            chart.getXAxis().setValueFormatter(xFormatter); //set the formatter created to the x axis
            //The following code changes the graph styling
            chart.setDrawGridBackground(false); //set background to transparent
            chart.setDrawValueAboveBar(true); //value is drawn inside the bar on the top
            chart.getAxisLeft().setEnabled(false); //the left Y axis is set invisible
            chart.getAxisRight().setEnabled(false); //the right Y axis is set invisible
            chart.getXAxis().setDrawGridLines(false); //the grid lines are set off
            chart.getXAxis().setLabelCount(days.size()); //this sets the count of the labels to match the count of days in the file
            chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); //this sets the label to the bottom
            chart.getXAxis().setTextSize((float) 19); //set text size a bit higher so it is easier to see
            chart.setExtraBottomOffset(2); //set a little extra offset so the text doesn't get cut off
            chart.getDescription().setEnabled(false); //this sets the description invisible
            chart.setTouchEnabled(false); //this makes interacting with the graph impossible
            chart.getLegend().setEnabled(false); //this sets the legend invisible, it is not needed as we only have one data set
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
