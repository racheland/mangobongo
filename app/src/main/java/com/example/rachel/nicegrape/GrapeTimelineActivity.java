package com.example.rachel.nicegrape;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.rachel.nicegrape.model.Sticker;
import com.example.rachel.nicegrape.sticker.TimelineAdapter;

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

    public static final String KEY_STICKER_LIST = "STICKER_LIST";

    private String pattern = "MM/dd/yyyy HH:mm:ss";
    private DateFormat df = new SimpleDateFormat(pattern);

    private List<Sticker> stickerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timeline);

        stickerList = getIntent().getParcelableArrayListExtra(KEY_STICKER_LIST);
        stickerList = Observable.fromIterable(stickerList).filter(new io.reactivex.functions.Predicate<Sticker>() {
            @Override
            public boolean test(Sticker sticker) {
                return sticker.isActivate();
            }
        }).toList().blockingGet();


        TimeLineRecyclerView recyclerView = findViewById(R.id.timeline_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(getSectionCallback(stickerList));

        recyclerView.setAdapter(new TimelineAdapter(stickerList));
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