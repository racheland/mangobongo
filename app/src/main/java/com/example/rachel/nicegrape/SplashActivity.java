package com.example.rachel.nicegrape;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.rachel.nicegrape.util.NumGrapeDialog;
import com.example.rachel.nicegrape.util.PreferenceHelper;
import com.example.rachel.nicegrape.util.TitleNameDialog;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    public static final String IS_FIRST_RUN = "isFirstRun";
    public static final String INITIAL_VALUE = "true";

    private TextView userNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        userNameTextView = findViewById(R.id.username);

        final String string = PreferenceHelper.readData(IS_FIRST_RUN, this);
        if (!INITIAL_VALUE.equals(string)) {
            final TitleNameDialog titleNameDialog = new TitleNameDialog();

            titleNameDialog.getBuilder(this).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    String userName = titleNameDialog.getEditText().getText().toString();
                    PreferenceHelper.writeData("userName", userName, SplashActivity.this);

                    userNameTextView.setText(userName);
                }
            });

            titleNameDialog.show(getSupportFragmentManager(), "TitleFragment");
        }

        userNameTextView.setText(PreferenceHelper.readData("userName",SplashActivity.this));
        PreferenceHelper.writeData(IS_FIRST_RUN, INITIAL_VALUE, this);


    }



    public void onClickEnterButton(View view) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }
}