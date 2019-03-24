package com.example.rachel.nicegrape.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.rachel.nicegrape.R;

import androidx.annotation.Nullable;

public class SettingOpenLicense extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_open_license);
    }

    public void onClickBackSetting (View view){
        finish();
    }
}
