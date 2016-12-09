package com.epicodus.bookview.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.epicodus.bookview.R;

public class CreateAccountActivity extends AppCompatActivity {
    TextView mLogoTextView = (TextView) findViewById(R.id.logoTextView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mLogoTextView.setTypeface(pacificoFont);
    }
}
