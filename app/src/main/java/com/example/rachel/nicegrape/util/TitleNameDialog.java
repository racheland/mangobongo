package com.example.rachel.nicegrape.util;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.rachel.nicegrape.R;

import androidx.fragment.app.DialogFragment;

public class TitleNameDialog extends DialogFragment {

    private Builder builder;
    private EditText editText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.name_signin, null);
        editText = view.findViewById(R.id.username);
        builder.setView(view);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                TitleNameDialog.this.getDialog().cancel();
            }
        });// Add action buttons
        return builder.create();
    }

    public Builder getBuilder(Context context) {
        if (builder == null) {
           builder = new Builder(context);
        }
        return builder;
    }

    public EditText getEditText() {
        return editText;
    }
}
