package com.example.wellneschecker;

import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

class RecommendationHandler {

    String recommendation;

    RecommendationHandler(AssetManager asm, String condition) {
        String line;
        BufferedReader reader = null;
        int random = new Random().nextInt(2);
        try {
            InputStream inputStream = asm.open("activity.csv");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine())!= null) {
                if (condition.equals(line.split(";")[0]) && random == Integer.parseInt(line.split(";")[1])) {
                    recommendation = line.split(";")[2];
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //Returns recommendation string
    String getRecommendation() {
        return recommendation;
    }
}
