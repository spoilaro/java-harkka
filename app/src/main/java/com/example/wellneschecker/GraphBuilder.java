package com.example.wellneschecker;

import android.content.Context;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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

    void addToCSV(Context context, String data, String username) { //method that shouldn't allow to add more than 7 lines to the log file
        int x = 0;
        File tempFile = new File(context.getFilesDir(), String.format("temp.txt"));
        File file = new File(context.getFilesDir(), String.format("%s.csv", username));
        String line = "";

        try {
            Scanner scan = new Scanner(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            //get the number of lines in the file
            while (br.readLine() != null) {
                if(scan.hasNextLine())
                    line = scan.nextLine();
                x++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(line);
        if (x == 0) { //if the number of lines is 0, just add the new data
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write(data + "\n");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (line.split(" ")[1].equals(data.split(" ")[1])) {
            try {
                Scanner scanFile = new Scanner(file);
                BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));
                while (scanFile.hasNextLine()) {
                    System.out.println("11111111111111");
                    String next = scanFile.nextLine(); //get the next line and save it to a string
                    if (next.split(" ")[1].equals(data.split(" ")[1])){
                        out.write(String.format("%d %s\n", Integer.parseInt(data.split(" ")[0])+Integer.parseInt(next.split(" ")[0]), data.split(" ")[1]));
                    }
                    else
                        out.write(next + "\n");
                }
                out.close();
                scanFile.close();
                boolean success = tempFile.renameTo(file);
                if (!success)
                    System.out.println("fail");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (x > 6) { //if the number of lines is greater than 6, remove the first line and then copy every line after that and add the new data
            try {
                Scanner scanFile = new Scanner(file);
                //System.out.println("#############111##############");

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
        } else { // if the number of lines is something between 0 and 8, copy every line and add the new data
            try {
                Scanner scanFile = new Scanner(file);
                BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));
                while (scanFile.hasNextLine()) {
                    String next = scanFile.nextLine();
                    out.write(next+"\n");
                }
                out.write(data+"\n");
                out.close();
                boolean success = tempFile.renameTo(file);
                if (!success)
                    System.out.println("Failed to rename file");
                scanFile.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void readCSV(Context context, BarChart chart, String username) {
        int x = 0;
        ArrayList<String> days = new ArrayList<>();
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        String line;
        File file = new File(context.getFilesDir(), String.format("%s.csv", username));
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
            dataSet.setGradientColor(0x77b61219, 0x77b61219);
            chart.setData(data);
            //here we create a new value formatter that lets us set the x axis values to represent the dates
            ValueFormatter xFormatter = new ValueFormatter() {
                @Override
                public String getAxisLabel(float value, AxisBase axis) {
                    if ((int) value == days.size())
                        return days.get((int)value - 1);
                    else
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
            chart.setExtraBottomOffset(4); //set a little extra offset so the text doesn't get cut off
            chart.getDescription().setEnabled(false); //this sets the description invisible
            chart.setTouchEnabled(false); //this makes interacting with the graph impossible
            chart.getLegend().setEnabled(false); //this sets the legend invisible, it is not needed as we only have one data set
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
