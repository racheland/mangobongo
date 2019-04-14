package com.example.rachel.nicegrape.setting;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.rachel.nicegrape.R;
import com.example.rachel.nicegrape.SplashActivity;
import com.example.rachel.nicegrape.pin.CustomPinActivity;
import com.example.rachel.nicegrape.util.PreferenceHelper;
import com.example.rachel.nicegrape.util.TitleNameDialog;
import com.github.omadahealth.lollipin.lib.managers.AppLock;

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

    public void onClickGrapeCalendar (View view) {
        Intent intent = new Intent( SettingActivity.this, SettingCalendar.class);
        startActivity(intent);
    }

    public void onClickOpenLicense (View view) {
        Intent intent = new Intent( SettingActivity.this, SettingOpenLicense.class);
        startActivity(intent);
    }


}
