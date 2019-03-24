package com.example.rachel.nicegrape.sticker;


import com.example.rachel.nicegrape.model.Grape;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private List<Grape> grapeList;

    public PagerAdapter(FragmentManager fm, List<Grape> grapeList) {
        super(fm);
        this.grapeList = grapeList;
    }

    @Override
    public Fragment getItem(int position) {
        return new GrapeFragment(grapeList.get(position).getStickerList());
    }

    @Override
    public int getCount() {
        return grapeList.size();
    }
}