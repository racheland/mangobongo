package com.onhand.rachel.nicegrape.setting;

import android.os.Bundle;
import android.view.View;

import com.onhand.rachel.nicegrape.R;
import com.onhand.rachel.nicegrape.model.Grape;
import com.onhand.rachel.nicegrape.sticker.GrapeEditAdapter;
import com.onhand.rachel.nicegrape.util.PreferenceHelper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SettingTitleActivity extends AppCompatActivity {

    private List<Grape> grapeList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_title);

        MobileAds.initialize(this, "ca-app-pub-2815505421993509~3739900233");
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
