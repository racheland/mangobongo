package com.example.rachel.nicegrape;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
    }

    public void onClickEnterButton(View view) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }
}