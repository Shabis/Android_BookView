package com.epicodus.bookview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    private TextView mWelcomeUserTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mWelcomeUserTextView = (TextView) findViewById(R.id.welcomeUserTextView);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mWelcomeUserTextView.setText("Welcome " + name + "!");
    }
}
