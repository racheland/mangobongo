package com.example.rachel.nicegrape;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.rachel.nicegrape.model.Grape;
import com.example.rachel.nicegrape.model.Sticker;
import com.example.rachel.nicegrape.util.PreferenceHelper;
import com.example.rachel.nicegrape.util.TitleNameDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    public static final String IS_FIRST_RUN = "isFirstRun";
    public static final String INITIAL_VALUE = "true";

    private TextView userNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        userNameTextView = findViewById(R.id.username);

        final String string = PreferenceHelper.readData(IS_FIRST_RUN, this);
        if (!INITIAL_VALUE.equals(string)) {
            final TitleNameDialog titleNameDialog = new TitleNameDialog();

            titleNameDialog.getBuilder(this).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    String userName = titleNameDialog.getEditText().getText().toString();
                    PreferenceHelper.writeData("userName", userName, SplashActivity.this);
                    userNameTextView.setText(userName);

                    String grapeName = userName;
                    int grapeSize = 10;

                    ArrayList<Sticker> stickers = new ArrayList<>();
                    for (int i = 0; i < grapeSize; i++) {
                        stickers.add(new Sticker(false, "", new Date()));
                    }

                    Grape grape = new Grape(grapeName, stickers);
                    PreferenceHelper.addGrape(grape, SplashActivity.this);
                }
            });

            titleNameDialog.show(getSupportFragmentManager(), "TitleFragment");
        }

        userNameTextView.setText(PreferenceHelper.readData("userName",SplashActivity.this));
        PreferenceHelper.writeData(IS_FIRST_RUN, INITIAL_VALUE, this);
    }

    public void onClickEnterButton(View view) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }
}