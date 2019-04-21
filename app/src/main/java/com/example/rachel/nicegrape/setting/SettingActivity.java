package com.example.rachel.nicegrape.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.example.rachel.nicegrape.R;
import com.example.rachel.nicegrape.pin.CustomPinActivity;
import com.github.omadahealth.lollipin.lib.managers.AppLock;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static com.example.rachel.nicegrape.sticker.GrapeFragment.REQUEST_CODE_PIN;

public class SettingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
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


}
