package com.example.wellneschecker;

import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RecommendationHandler {

    RecommendationHandler(AssetManager asm){
        String line;
        StringBuilder strFile = new StringBuilder();
        BufferedReader reader = null;
        try {
            InputStream inputStream = asm.open("activity.csv");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine())!= null) {

                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
