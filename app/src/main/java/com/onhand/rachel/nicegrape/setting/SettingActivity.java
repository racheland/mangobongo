package com.onhand.rachel.nicegrape.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.onhand.rachel.nicegrape.R;
import com.onhand.rachel.nicegrape.pin.CustomPinActivity;
import com.github.omadahealth.lollipin.lib.managers.AppLock;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static com.onhand.rachel.nicegrape.sticker.GrapeFragment.REQUEST_CODE_PIN;

public class SettingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        Intent intent = new Intent(this, CustomPinActivity.class);
        intent.putExtra(AppLock.EXTRA_TYPE, AppLock.UNLOCK_PIN);
        startActivityForResult(intent, REQUEST_CODE_PIN);

        MobileAds.initialize(this, "ca-app-pub-2815505421993509/1990159701");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void onClickBackMain (View view){
        finish();
    }

    public void onClickGrapeTitle (View view) {
        Intent intent = new Intent(SettingActivity.this, SettingTitleActivity.class);
        startActivity(intent);
    }

    public void onClickSwitch(View view) {
        Switch switchView = ((Switch)view);
//        switchView.get
    }

    public void onClickOpenLicense (View view) {
        Intent intent = new Intent( SettingActivity.this, SettingOpenLicense.class);
        startActivity(intent);
    }

    public void onClickPasswordChange(View view) {
        Intent intent = new Intent(this, CustomPinActivity.class);
        intent.putExtra(AppLock.EXTRA_TYPE, AppLock.CHANGE_PIN);
        startActivityForResult(intent, REQUEST_CODE_PIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PIN && resultCode != RESULT_OK) {
            finish();
        }
    }
}
