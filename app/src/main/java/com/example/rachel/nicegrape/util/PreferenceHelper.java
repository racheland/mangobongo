package com.example.rachel.nicegrape.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.rachel.nicegrape.model.Grape;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PreferenceHelper {

    private static final String DATABASE_NAME = "database";
    private static final int GRAPE_KEY_MAX = 30;
    private static final String GRAPE_KEY = "grape_";

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

    public static void writeGrape(int index, Grape grape, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(GRAPE_KEY + index, grape.toJson());
        editor.apply();
    }

    public static void addGrape(Grape grape, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);

        for (int i = 0; i < GRAPE_KEY_MAX; i++) {
            if (!preferences.contains(GRAPE_KEY + i)) {
                writeGrape(i, grape, context);
                break;
            }
        }
    }


    public static void writeGrapeList(List<Grape> grapeList, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        for (int index = 0; index < grapeList.size(); index ++) {
            editor.putString(GRAPE_KEY + index, grapeList.get(index).toJson());
            editor.apply();
        }
    }

    public static Grape readGrape(int index, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();

        if (preferences.contains(GRAPE_KEY + index)) {
            String grapeJson = preferences.getString(GRAPE_KEY + index, "");
            Grape grape = gson.fromJson(grapeJson, Grape.class);
            return grape;
        }

        return null;
    }

    public static List<Grape> readGrape(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        List<Grape> grapeList = new ArrayList<>();
        for (int index = 0; index < GRAPE_KEY_MAX; index++) {
            if (preferences.contains(GRAPE_KEY + index)) {
                String grapeJson = preferences.getString(GRAPE_KEY + index, "");
                Grape grape = gson.fromJson(grapeJson, Grape.class);
                grapeList.add(grape);
            }
        }

        return grapeList;
    }

}
