package com.onhand.rachel.nicegrape.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

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

        MobileAds.initialize(this, "ca-app-pub-2815505421993509~3739900233");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        WebView webView = findViewById(R.id.web_view);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl("file:///android_asset/license.html");
    }

    public void onClickBackSetting (View view){
        finish();
    }
}
