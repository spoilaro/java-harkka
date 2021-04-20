package com.example.wellneschecker;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphBuilder {

    void createDefaultCSV(Context context) throws IOException {
        File file = new File(context.getFilesDir(), String.format("move.csv"));
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("1;0;1;2;3" +
                "2;3;1;0;4");
        bufferedWriter.close();
        System.out.println("#########DONE CSV##########");
    }

}
