package com.example.rachel.nicegrape.sticker;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.rachel.nicegrape.R;

import java.util.concurrent.Callable;

@SuppressLint("ValidFragment")
public class GrapeFragment extends Fragment {

    public static final int GRAPE_TYPE_5 = 5;
    public static final int GRAPE_TYPE_10 = 10;
    public static final int GRAPE_TYPE_20 = 20;
    public static final int GRAPE_TYPE_30 = 30;
    private int grapeCount;
    public GrapeFragment(int grapeCount) {
        this.grapeCount = grapeCount;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        switch (grapeCount) {
            case GRAPE_TYPE_5:
                view = inflater.inflate(R.layout.layout_grape_5, container, false);
                break;
            case GRAPE_TYPE_10:
                view = inflater.inflate(R.layout.layout_grape_10, container, false);
                break;
            case GRAPE_TYPE_20:
                view = inflater.inflate(R.layout.layout_grape_20, container, false);
                break;
            case GRAPE_TYPE_30:
                view = inflater.inflate(R.layout.layout_grape_30, container, false);
                break;
            default:
                view = inflater.inflate(R.layout.layout_grape_5, container, false);
                break;
        }

        RelativeLayout parent = view.findViewById(R.id.grape_container);
        recursiveLoopChildren(parent, new GenericRunnable<View>() {
            @Override
            public void run(final View view) {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        if (view.getId() != R.id.grape_leaf) {
                            view.setOnDragListener(new DragListener());
                        }
                    }
                });
            }
        });

        return view;
    }

    public void recursiveLoopChildren(ViewGroup parent, GenericRunnable<View> runnable) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                recursiveLoopChildren((ViewGroup) child, runnable);
            } else {
                if (child != null) {
                    try {
                        runnable.run(child);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private class DragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.grape_1);
        Drawable normalShape = getResources().getDrawable(R.drawable.grape_basic_1);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    ((ImageView)v).setImageDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    ((ImageView)v).setImageDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    ((ImageView)v).setImageDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    public interface GenericRunnable<T> {
        void run(T t);
    }
}
