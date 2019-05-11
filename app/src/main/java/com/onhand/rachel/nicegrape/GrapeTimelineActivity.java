package com.onhand.rachel.nicegrape;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.onhand.rachel.nicegrape.model.Sticker;
import com.onhand.rachel.nicegrape.sticker.GrapeFragment;
import com.onhand.rachel.nicegrape.sticker.TimelineAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import xyz.sangcomz.stickytimelineview.RecyclerSectionItemDecoration;
import xyz.sangcomz.stickytimelineview.TimeLineRecyclerView;
import xyz.sangcomz.stickytimelineview.model.SectionInfo;

public class GrapeTimelineActivity extends AppCompatActivity {

    public static final String KEY_STICKER_INDEX = "STICKER_INDEX";

    private String pattern = "yyyy년 MM월 dd일 HH시 mm분";
    private DateFormat df = new SimpleDateFormat(pattern);

    private List<Sticker> stickerList;
    private TimelineAdapter timelineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timeline);

       int index = getIntent().getIntExtra(KEY_STICKER_INDEX, 0);
        stickerList = GrapeFragment.grapeList.get(index).getStickerList();

        TimeLineRecyclerView recyclerView = findViewById(R.id.timeline_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(getSectionCallback(stickerList));

        timelineAdapter = new TimelineAdapter(stickerList);
        refreshStickerList();
        recyclerView.setAdapter(timelineAdapter);
    }

    public void refreshStickerList() {
        List<Sticker> refreshedList = Observable.fromIterable(stickerList).filter(new io.reactivex.functions.Predicate<Sticker>() {
            @Override
            public boolean test(Sticker sticker) {
                return sticker.isActivate();
            }
        }).toList().blockingGet();

        timelineAdapter.setStickerList(refreshedList);
    }

    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final List<Sticker> singerList) {
        return new RecyclerSectionItemDecoration.SectionCallback() {

            @Nullable
            @Override
            public SectionInfo getSectionHeader(int position) {
                Sticker sticker = singerList.get(position);
                Drawable dot = getResources().getDrawable(R.drawable.grape_1);
                return new SectionInfo(df.format(sticker.getCreateDate()), "", dot);
            }

            @Override
            public boolean isSection(int position) {
                return true;
            }
        };
    }
}