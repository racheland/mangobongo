package com.example.rachel.nicegrape;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rachel.nicegrape.model.Grape;
import com.example.rachel.nicegrape.model.Sticker;
import com.example.rachel.nicegrape.pin.CustomPinActivity;
import com.example.rachel.nicegrape.sticker.PagerAdapter;
import com.github.omadahealth.lollipin.lib.PinActivity;
import com.github.omadahealth.lollipin.lib.managers.AppLock;
import com.github.omadahealth.lollipin.lib.managers.AppLockActivity;
import com.github.omadahealth.lollipin.lib.managers.LockManager;
import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        pageIndicatorView = findViewById(R.id.page_indicator);

        rightArraow = findViewById(R.id.right_arrow);
        leftArrow = findViewById(R.id.left_arrow);
        titleView = findViewById(R.id.main_purpose_txtv);
        stickerAddView = findViewById(R.id.add_grape_1);

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

//        Intent intent = new Intent(MainActivity.this, CustomPinActivity.class);
//        intent.putExtra(AppLock.EXTRA_TYPE, AppLock.UNLOCK_PIN);
//        startActivityForResult(intent, REQUEST_CODE_ENABLE);

        viewPager.setPageTransformer(false, new FadePageTransformer());
        stickerAddView.setOnTouchListener(new DragTouchListener());
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

    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.grape_1);
        Drawable normalShape = getResources().getDrawable(R.drawable.grape_basic_1);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
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
