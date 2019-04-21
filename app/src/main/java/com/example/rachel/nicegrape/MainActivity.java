package com.example.rachel.nicegrape;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.rachel.nicegrape.model.Grape;
import com.example.rachel.nicegrape.model.Sticker;
import com.example.rachel.nicegrape.setting.SettingActivity;
import com.example.rachel.nicegrape.sticker.PagerAdapter;
import com.example.rachel.nicegrape.util.NumGrapeDialog;
import com.example.rachel.nicegrape.util.PreferenceHelper;
import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ENABLE = 100;
    private ViewPager viewPager;
    private PageIndicatorView pageIndicatorView;
    private PagerAdapter adapter;

    private View rightArraow;
    private View leftArrow;
    private TextView titleView;
    private View stickerAddView;

    private List<Grape> grapeList;

    @Override
    public void onPause() {
        super.onPause();
        PreferenceHelper.writeGrapeList(grapeList, this);
        }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        pageIndicatorView = findViewById(R.id.page_indicator);

        rightArraow = findViewById(R.id.right_arrow);
        leftArrow = findViewById(R.id.left_arrow);
        titleView = findViewById(R.id.main_purpose_txtv);
        stickerAddView = findViewById(R.id.add_grape_1);

        grapeList = PreferenceHelper.readGrape(this);

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

        viewPager.setPageTransformer(false, new FadePageTransformer());
        stickerAddView.setOnTouchListener(new DragTouchListener());
    }

    @Override
    protected void onResume() {
        super.onResume();

        grapeList = PreferenceHelper.readGrape(this);
        adapter.notifyDataSetChanged();

        onPageSelected(viewPager.getCurrentItem());
    }

    public void onClickSettingButton (View view) {
        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
        startActivity(intent);
    }

    public void onClickArrow(View view) {
        int position = viewPager.getCurrentItem();

        //왼쪽 화살표 클릭
        if (view.getId() == R.id.left_arrow) {
            viewPager.setCurrentItem(Math.max(0, position - 1));
            //오른쪽 화살표 클릭
        } else {
            viewPager.setCurrentItem(Math.max(viewPager.getChildCount(), position + 1));
        }
    }

    public void onClickAddGrape (View view) {
        final NumGrapeDialog numGrapeDialog = new NumGrapeDialog();
        numGrapeDialog.getBuilder(this).setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String grapeName = numGrapeDialog.getEditText().getText().toString();
                int grapeSize = Integer.parseInt(numGrapeDialog.getNumberPicker().getDisplayedValues()[numGrapeDialog.getNumberPicker().getValue()]);

                ArrayList<Sticker> stickers = new ArrayList<>();
                for (int i = 0; i < grapeSize; i++) {
                    stickers.add(new Sticker(false, "", new Date()));
                }

                Grape grape = new Grape(grapeName, stickers);
                grapeList.add(grape);
                PreferenceHelper.writeGrapeList(grapeList, MainActivity.this);
                adapter.notifyDataSetChanged();
                onPageSelected(viewPager.getCurrentItem());
            }
        });

        numGrapeDialog.show(getSupportFragmentManager(), "NumGrapeDialog");
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

    private final class DragTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    public class FadePageTransformer implements ViewPager.PageTransformer {
        public void transformPage(View view, float position) {

            if (position < -1 || position > 1) {
                view.setAlpha(0);
            }
            else if (position <= 0 || position <= 1) {
                // Calculate alpha. Position is decimal in [-1,0] or [0,1]
                float alpha = (position <= 0) ? position + 1 : 1 - position;
                view.setAlpha(alpha);
            }
            else if (position == 0) {
                view.setAlpha(1);

            }
        }
    }


}
