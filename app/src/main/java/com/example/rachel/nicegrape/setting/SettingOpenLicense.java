package com.example.rachel.nicegrape.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.rachel.nicegrape.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import androidx.annotation.Nullable;

public class SettingOpenLicense extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_open_license);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void onClickBackSetting (View view){
        finish();
    }
}
