package com.example.rachel.nicegrape.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.rachel.nicegrape.R;

public class SettingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
    }

    public void onClickBackMain (View view){
        finish();
    }

    public void onClickGrapeTitle (View view) {
        Intent intent = new Intent(SettingActivity.this, SettingTitleActivity.class);
        startActivity(intent);
    }
}
