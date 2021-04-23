package com.example.wellneschecker;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.BufferedReader;

public class RecommendationHandler {

    RecommendationHandler(AssetManager asm){
        StringBuilder strFile = new StringBuilder();
        BufferedReader reader = null;
        InputStream inputStream = asm.getAssets().open("activity.csv");
    }

}
