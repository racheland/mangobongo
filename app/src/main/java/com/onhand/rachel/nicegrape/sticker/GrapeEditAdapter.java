package com.onhand.rachel.nicegrape.sticker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.onhand.rachel.nicegrape.R;
import com.onhand.rachel.nicegrape.model.Grape;
import com.onhand.rachel.nicegrape.util.PreferenceHelper;
import com.onhand.rachel.nicegrape.util.TitleNameDialog;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
    public void onBindViewHolder(@NonNull final GrapeViewHolder holder, final int position) {
        holder.grapeName.setText(grapeList.get(position).getTitle());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(holder.btnDelete.getContext()).setCancelable(true)
                        .setTitle(holder.btnEdit.getContext().getString(R.string.grape_title_change))
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        grapeList.remove(position);
                        PreferenceHelper.writeGrapeList(grapeList, v.getContext());
                        notifyDataSetChanged();
                    }
                }).show();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickEdit(v, position);
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
            grapeName = itemView.findViewById(R.id.content);
            btnEdit = itemView.findViewById(R.id.grape_title_edit);
            btnDelete = itemView.findViewById(R.id.delete);
        }
    }

    public void onClickEdit(final View view, final int index) {
        final TitleNameDialog grapeTitleEdit = new TitleNameDialog("포도 제목을 설정해주세요!");
        grapeTitleEdit.getBuilder(view.getContext()).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String grapeTitle = grapeTitleEdit.getEditText().getText().toString();
                Grape grape = grapeList.get(index);
                grape.setTitle(grapeTitle);
                PreferenceHelper.writeGrapeList(grapeList, view.getContext());
                notifyDataSetChanged();
            }
        }).setCancelable(false);

        grapeTitleEdit.show(((AppCompatActivity)view.getContext()).getSupportFragmentManager(), "TitleFragment");
    }


}
