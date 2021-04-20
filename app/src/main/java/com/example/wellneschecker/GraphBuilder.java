package com.example.wellneschecker;

import android.content.Context;

import com.github.mikephil.charting.charts.BarChart;
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

public class GraphBuilder {

    GraphBuilder() {
        DateHandler dh = new DateHandler();
    }

    void createDefaultCSV(Context context) throws IOException {
        File file = new File(context.getFilesDir(), String.format("move.csv"));
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("4 1.2\n");
        bufferedWriter.write("5 2.2\n");
        bufferedWriter.write("6 3.2");
        bufferedWriter.close();
        System.out.println("#########DONE CSV##########");
    }

    void readCSV(Context context, BarChart chart) {
        int x = 0;
        ArrayList<String> days = new ArrayList<>();
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        String line;
        File file = new File(context.getFilesDir(), String.format("move.csv"));
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            //this loop creates a list of entries that we need to create the graph
            while((line = br.readLine())!=null){
                entries.add(new BarEntry(x, Integer.parseInt(line.split(" ")[0])));
                days.add(line.split(" ")[1]);
                x++;
            }
            BarDataSet dataSet = new BarDataSet(entries, "Tunnit"); //creates a new data set required to make the data below
            BarData data = new BarData(dataSet); //this data is required to draw the actual graph
            chart.setData(data);
            for (String s : days) {
                System.out.println("####");
                System.out.println(s);
                System.out.println("####");
            }

            ValueFormatter xformatter = new ValueFormatter() {
                @Override
                public String getAxisLabel(float value, AxisBase axis) {
                    return days.get((int)value);
                }
            };
            chart.getXAxis().setValueFormatter(xformatter);
            chart.setDrawGridBackground(true);
            chart.setDrawValueAboveBar(false);
            chart.getAxisLeft().setEnabled(false);
            chart.getAxisRight().setEnabled(false);
            chart.getXAxis().setDrawGridLines(false);
            chart.getXAxis().setLabelCount(days.size());
            chart.getXAxis().setDrawLabels(true);
            chart.getXAxis().setPosition(XAxis.XAxisPosition.TOP_INSIDE);
            chart.getDescription().setEnabled(false);
            chart.getLabelFor(
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
