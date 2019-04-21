package com.onhand.rachel.nicegrape.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.onhand.rachel.nicegrape.model.Grape;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreferenceHelper {

    private static final String DATABASE_NAME = "database";
    private static final int GRAPE_KEY_MAX = 30;
    private static final String GRAPE_KEY = "grapeList";

    public static String readData(String key, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    public static void writeData(String key, String value, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void writeGrapeList(List<Grape> grapeList, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();

        editor.putString(GRAPE_KEY, gson.toJson(grapeList.toArray()));
        editor.apply();
    }

    public static List<Grape> readGrape(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String grapeJson = preferences.getString(GRAPE_KEY, "");
        Grape[] grapes = gson.fromJson(grapeJson, Grape[].class);

        List<Grape> grapeList = new ArrayList<>(Arrays.asList(grapes));

        return grapeList;
    }

}
