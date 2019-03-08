package com.example.rachel.nicegrape.sticker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rachel.nicegrape.R;

@SuppressLint("ValidFragment")
public class GrapeFragment extends Fragment {

    private int grapeCount;
    public GrapeFragment(int grapeCount) {
        this.grapeCount = grapeCount;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch (grapeCount) {
            case 5:
                return inflater.inflate(R.layout.layout_grape_5, container, false);
            case 10:
                return inflater.inflate(R.layout.layout_grape_10, container, false);
            case 20:
                return inflater.inflate(R.layout.layout_grape_20, container, false);
            case 30:
                return inflater.inflate(R.layout.layout_grape_30, container, false);
            default:
                return inflater.inflate(R.layout.layout_grape_5, container, false);

        }
    }
}
