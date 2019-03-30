package com.example.rachel.nicegrape.sticker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rachel.nicegrape.R;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_grape_title, null, false);
        return new GrapeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GrapeViewHolder holder, int position) {
        holder.grapeName.setText(grapeList.get(position).getTitle());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
