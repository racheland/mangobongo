package com.onhand.rachel.nicegrape.sticker;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.omadahealth.lollipin.lib.managers.AppLock;
import com.onhand.rachel.nicegrape.GoodJobActivity;
import com.onhand.rachel.nicegrape.GrapeTimelineActivity;
import com.onhand.rachel.nicegrape.MainActivity;
import com.onhand.rachel.nicegrape.R;
import com.onhand.rachel.nicegrape.model.Grape;
import com.onhand.rachel.nicegrape.model.Sticker;
import com.onhand.rachel.nicegrape.pin.CustomPinActivity;
import com.onhand.rachel.nicegrape.util.PreferenceHelper;
import com.onhand.rachel.nicegrape.util.TitleNameDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;

@SuppressLint("ValidFragment")
public class GrapeFragment extends Fragment {

    public static final int GRAPE_TYPE_5 = 5;public static final int GRAPE_TYPE_10 = 10;
    public static final int GRAPE_TYPE_20 = 20;
    public static final int GRAPE_TYPE_30 = 30;
    public static final int REQUEST_CODE_PIN = 101;

    public static List<Grape> grapeList;
    private int position;
    public ArrayList<Sticker> stickerList;
    private int grapeCount;

    private View rootView;

    public GrapeFragment(List<Grape> grapeList, int position) {
        this.grapeCount = grapeList.get(position).getStickerList().size();
        this.stickerList = grapeList.get(position).getStickerList();
        this.grapeList = grapeList;
        this.position = position;
    }

    @Override
    public void onResume() {
        super.onResume();

        PreferenceHelper.writeGrapeList(grapeList, getContext());
        ((MainActivity)getActivity()).refresh();
        refreshViews(rootView);
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

        refreshViews(view);
        return view;
    }

    public void refreshViews(View view) {
        RelativeLayout parent = view.findViewById(R.id.grape_container);
        recursiveLoopChildren(parent, new GenericRunnable<View>() {
            int index = 0;

            @Override
            public void run(final View view) {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        if (view.getId() != R.id.grape_leaf) {
                            if (stickerList.get(index).isActivate()) {
                                ((ImageView) view).setImageDrawable(getResources().getDrawable(R.drawable.grape_1));
                            } else {
                                ((ImageView) view).setImageDrawable(getResources().getDrawable(R.drawable.grape_basic_1));
                            }
                            view.setTag(index++);
                            view.setOnDragListener(new DragListener());
                        }
                    }
                });

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), GrapeTimelineActivity.class);
                        intent.putExtra(GrapeTimelineActivity.KEY_STICKER_INDEX, position);
                        startActivity(intent);
                    }
                });
            }
        });

        rootView = view;
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
            Sticker sticker = stickerList.get(Integer.parseInt(v.getTag().toString()));
            if (sticker.isActivate()) {
                return true;
            }

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    ((ImageView)v).setImageDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    if (!sticker.isActivate()) {
                        ((ImageView)v).setImageDrawable(normalShape);
                    }
                    break;
                case DragEvent.ACTION_DROP:
                    if (!isDropped && !sticker.isActivate()) {
                        currentStickerIndex = Integer.parseInt(v.getTag().toString());
                        isDropped = true;
                        currentSticker = sticker;
                        currentStickerView = v;
                        GrapeFragment.this.enterShape = enterShape;
                        GrapeFragment.this.normalShape = normalShape;
                        Intent intent = new Intent(getActivity(), CustomPinActivity.class);
                        intent.putExtra(AppLock.EXTRA_TYPE, AppLock.UNLOCK_PIN);
                        startActivityForResult(intent, REQUEST_CODE_PIN);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    private boolean isDropped = false;
    private View currentStickerView;
    private Sticker currentSticker;
    private int currentStickerIndex;
    public Drawable enterShape;
    public Drawable normalShape;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PIN && resultCode == RESULT_OK) {
            final TitleNameDialog titleNameDialog = new TitleNameDialog("칭찬 내용을 입력하세요!");

            titleNameDialog.getBuilder(getActivity()).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    currentSticker = stickerList.get(Integer.parseInt(String.valueOf(currentStickerIndex)));
                    currentStickerView = rootView.findViewWithTag(Integer.parseInt(String.valueOf(currentStickerIndex)));

                    String contentString = titleNameDialog.getEditText().getText().toString();
                    ((ImageView)currentStickerView).setImageDrawable(enterShape);
                    currentSticker.setActivate(true);
                    currentSticker.setContent(contentString);
                    currentSticker.setCreateDate(new Date());

                    PreferenceHelper.writeGrapeList(grapeList, getContext());
                    ((MainActivity)getActivity()).refresh();

                    int count = 0;
                    List<Sticker> stickers = grapeList.get(position).getStickerList();
                    for (int index = 0; index <stickers.size(); index++) {
                        if (!stickers.get(index).isActivate()) {
                            return;
                        }
                        count++;
                    }

                    if (count == stickers.size()) {
                        Intent intent = new Intent(getContext(), GoodJobActivity.class);
                        startActivity(intent);
                    }
                }
            }).setCancelable(false);

            titleNameDialog.show(getActivity().getSupportFragmentManager(), "TitleFragment");
        } else {
            currentSticker = stickerList.get(Integer.parseInt(String.valueOf(currentStickerIndex)));
            currentStickerView = getView().findViewWithTag(Integer.parseInt(String.valueOf(currentStickerIndex)));

            ((ImageView)currentStickerView).setImageDrawable(normalShape);
            currentSticker.setActivate(false);
        }

        isDropped = false;
    }

    public interface GenericRunnable<T> {
        void run(T t);
    }
}