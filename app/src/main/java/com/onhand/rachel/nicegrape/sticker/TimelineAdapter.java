package com.onhand.rachel.nicegrape.sticker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.onhand.rachel.nicegrape.GrapeTimelineActivity;
import com.onhand.rachel.nicegrape.R;
import com.onhand.rachel.nicegrape.model.Sticker;
import com.onhand.rachel.nicegrape.util.TitleNameDialog;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.GrapeViewHolder> {

    private List<Sticker> stickerList;

    public TimelineAdapter(List<Sticker> stickerList) {
        this.stickerList = stickerList;
    }

    public void setStickerList(List<Sticker> stickerList) {
        this.stickerList = stickerList;
    }

    @NonNull
    @Override
    public GrapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_grape_title, null, false);
        return new GrapeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GrapeViewHolder holder, final int position) {
        Sticker currentSticker = stickerList.get(position);

        holder.grapeName.setText(currentSticker.getContent());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Sticker sticker = stickerList.get(position);
                new AlertDialog.Builder(v.getContext())
                        .setTitle("스티커를 삭제 할까요?")
                        .setMessage(sticker.getContent())
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sticker.setActivate(false);
                                sticker.setContent("");
                                notifyDataSetChanged();

                                ((GrapeTimelineActivity)v.getContext()).refreshStickerList();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { }})
                        .show();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final TitleNameDialog titleNameDialog = new TitleNameDialog("칭찬 내용을 입력하세요!");

                titleNameDialog.getBuilder(v.getContext()).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Sticker currentSticker = stickerList.get(position);
                        String contentString = titleNameDialog.getEditText().getText().toString();
                        currentSticker.setActivate(true);
                        currentSticker.setContent(contentString);
                        currentSticker.setCreateDate(new Date());
                        notifyDataSetChanged();

                        ((GrapeTimelineActivity)v.getContext()).refreshStickerList();
                    }
                }).setCancelable(false);

                titleNameDialog.show(((GrapeTimelineActivity)v.getContext()).getSupportFragmentManager(), "TitleFragment");
            }
        });
    }

    @Override
    public int getItemCount() {
        return stickerList.size();
    }

    class GrapeViewHolder extends RecyclerView.ViewHolder {
        private TextView grapeName;
        private ImageView btnEdit;
        private ImageView btnDelete;

        public GrapeViewHolder(@NonNull View itemView) {
            super(itemView);

            grapeName = itemView.findViewById(R.id.content);
            btnEdit = itemView.findViewById(R.id.grape_title_edit);
            btnDelete = itemView.findViewById(R.id.delete);
        }
    }

}
