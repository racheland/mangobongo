package com.example.rachel.nicegrape.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.example.rachel.nicegrape.R;

import androidx.fragment.app.DialogFragment;

public class NumGrapeDialog extends DialogFragment {
    private AlertDialog.Builder builder;
    private NumberPicker numberPicker;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.add_grape, null);
        numberPicker = view.findViewById(R.id.number_picker);

        final String[] values= {"5","10", "20", "30"};
        numberPicker.setDisplayedValues(values);
        numberPicker.setWrapSelectorWheel(true);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(values.length-1);

        builder.setView(view);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                NumGrapeDialog.this.getDialog().cancel();
            }
        });// Add action buttons
        return builder.create();
    }

    public AlertDialog.Builder getBuilder(Context context) {
        if (builder == null) {
            builder = new AlertDialog.Builder(context);
        }
        return builder;
    }

    public NumberPicker getNumberPicker() {
        return numberPicker;
    }

}
