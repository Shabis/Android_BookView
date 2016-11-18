package com.epicodus.bookview;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {
    @Bind(R.id.welcomeUserTextView) TextView mWelcomeUserTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mWelcomeUserTextView.setTypeface(pacificoFont);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mWelcomeUserTextView.setText("Welcome " + name + "!");
    }
}
