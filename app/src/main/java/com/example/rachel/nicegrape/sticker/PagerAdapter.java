package com.example.rachel.nicegrape.sticker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.rachel.nicegrape.model.Grape;

import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private List<Grape> grapeList;

    public PagerAdapter(FragmentManager fm, List<Grape> grapeList) {
        super(fm);
        this.grapeList = grapeList;
    }

    @Override
    public Fragment getItem(int position) {
        return new GrapeFragment(grapeList.get(position).getStickerList().size());
    }

    @Override
    public int getCount() {
        return grapeList.size();
    }
}