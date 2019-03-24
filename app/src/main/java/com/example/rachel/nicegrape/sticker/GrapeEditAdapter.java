package com.example.rachel.nicegrape.sticker;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rachel.nicegrape.model.Grape;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GrapeEditAdapter extends RecyclerView.Adapter<GrapeEditAdapter.GrapeViewHolder> {

    private List<Grape> grapeList;

    public GrapeEditAdapter(List<Grape> grapeList) {
        this.grapeList = grapeList;
    }

    @NonNull
    @Override
    public GrapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GrapeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return grapeList.size();
    }

    class GrapeViewHolder extends RecyclerView.ViewHolder {
        private TextView grapeName;
        private ImageView btnEdit;
        private ImageView btnDelete;

        public GrapeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
