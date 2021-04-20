package com.example.wellneschecker;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphBuilder {

    DateHandler dateHandler;


    GraphBuilder(){
        dateHandler = new DateHandler();

    };

    public void readMove(){

    };

    public void createDefaultCSV(Context context) throws IOException {

        String demodata = "" +
                "1 1.12.2020\n" +
                "3 2.12.2020\n" +
                "5 3.12.2020\n" +
                "2 4.12.2020\n" +
                "7 5.12.2020\n" +
                "3 6.12.2020\n" +
                "1 7.12.2020\n";

        File file = new File(context.getFilesDir(), String.format("move.txt"));
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(demodata);
        bufferedWriter.close();
        System.out.println("#########DONE CSV##########");
    }

}
