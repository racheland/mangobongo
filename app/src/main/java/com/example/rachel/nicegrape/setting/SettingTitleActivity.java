package com.example.rachel.nicegrape.setting;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.rachel.nicegrape.R;
import com.example.rachel.nicegrape.model.Grape;
import com.example.rachel.nicegrape.sticker.GrapeEditAdapter;
import com.example.rachel.nicegrape.sticker.TimelineAdapter;
import com.example.rachel.nicegrape.util.PreferenceHelper;
import com.example.rachel.nicegrape.util.TitleNameDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import xyz.sangcomz.stickytimelineview.TimeLineRecyclerView;

public class SettingTitleActivity extends AppCompatActivity {

    private List<Grape> grapeList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_title);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        grapeList = PreferenceHelper.readGrape(this);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new GrapeEditAdapter(grapeList));
    }

    public void onClickBackSetting(View view) {
        finish();
    }

}
