package com.example.rachel.nicegrape.util;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rachel.nicegrape.R;

import androidx.fragment.app.DialogFragment;

@SuppressLint("ValidFragment")
public class TitleNameDialog extends DialogFragment {

    private Builder builder;
    private EditText editText;

    private String title;

    @SuppressLint("ValidFragment")
    public TitleNameDialog(String title) {
        this.title = title;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.name_signin, null);
        editText = view.findViewById(R.id.username);
        ((TextView)view.findViewById(R.id.title)).setText(title);
        builder.setView(view);
        return builder.create();
    }

    public Builder getBuilder(Context context) {
        if (builder == null) {
           builder = new Builder(context);
        }
        return builder;
    }

    public Builder setNegativeButton() {
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                TitleNameDialog.this.getDialog().cancel();
            }
        });// Add action buttons

        return builder;
    }

    public EditText getEditText() {
        return editText;
    }
}
