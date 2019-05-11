package com.onhand.rachel.nicegrape.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.onhand.rachel.nicegrape.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import androidx.annotation.Nullable;

public class SettingOpenLicense extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_open_license);

        MobileAds.initialize(this, "cca-app-pub-2815505421993509/1990159701");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void onClickBackSetting (View view){
        finish();
    }
}
