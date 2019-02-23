package com.example.rachel.nicegrape.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static final String DATABASE_NAME = "database";

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
}
