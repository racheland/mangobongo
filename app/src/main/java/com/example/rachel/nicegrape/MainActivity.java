package com.example.rachel.nicegrape;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.rachel.nicegrape.model.Grape;
import com.example.rachel.nicegrape.model.Sticker;
import com.example.rachel.nicegrape.sticker.PagerAdapter;
import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PageIndicatorView pageIndicatorView;
    private PagerAdapter adapter;

    private View rightArraow;
    private View leftArrow;
    private TextView titleView;

    private List<Grape> grapeList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        pageIndicatorView = findViewById(R.id.page_indicator);

        rightArraow = findViewById(R.id.right_arrow);
        leftArrow = findViewById(R.id.left_arrow);
        titleView = findViewById(R.id.main_purpose_txtv);

        grapeList = new ArrayList<>();

        grapeList.add(new Grape("나이키 신발", new ArrayList<Sticker>(){{
            add(new Sticker(false));
            add(new Sticker(false));
            add(new Sticker(false));
            add(new Sticker(false));
            add(new Sticker(false));
        }}));

        grapeList.add(new Grape("미니카", new ArrayList<Sticker>(){{
            add(new Sticker(false));
            add(new Sticker(false));
            add(new Sticker(false));
            add(new Sticker(false));
            add(new Sticker(false));
            add(new Sticker(false));
            add(new Sticker(false));
            add(new Sticker(false));
            add(new Sticker(false));
            add(new Sticker(false));
        }}));


        adapter = new PagerAdapter(getSupportFragmentManager(), grapeList);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {/*empty*/}

            @Override
            public void onPageSelected(int position) {
                MainActivity.this.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {/*empty*/}
        });

        onPageSelected(0);
    }

    private void onPageSelected(int position) {
        pageIndicatorView.setSelection(position);

        if (grapeList.size() - 1 == position) {
            rightArraow.setVisibility(View.GONE);
        } else {
            rightArraow.setVisibility(View.VISIBLE);
        }

        if (0 == position) {
            leftArrow.setVisibility(View.GONE);
        } else {
            leftArrow.setVisibility(View.VISIBLE);
        }

        titleView.setText(grapeList.get(position).getTitle());
    }

}
