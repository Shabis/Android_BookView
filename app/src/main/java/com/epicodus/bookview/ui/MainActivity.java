package com.epicodus.bookview.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.bookview.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.submitButton) Button mSubmitButton;
    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.mainWelcome) TextView mMainWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mMainWelcome.setTypeface(pacificoFont);

        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mSubmitButton) {
            String name = mNameEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }
}
