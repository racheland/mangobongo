package com.example.rachel.nicegrape.sticker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rachel.nicegrape.R;
import com.example.rachel.nicegrape.model.Grape;
import com.example.rachel.nicegrape.model.Sticker;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.GrapeViewHolder> {

    private List<Sticker> stickerList;

    public TimelineAdapter(List<Sticker> stickerList) {
        this.stickerList = stickerList;
    }

    @NonNull
    @Override
    public GrapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_grape_title, null, false);
        return new GrapeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GrapeViewHolder holder, int position) {
        Sticker currentSticker = stickerList.get(position);
        holder.grapeName.setText(currentSticker.getContent());
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
