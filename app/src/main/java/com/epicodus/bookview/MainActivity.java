package com.epicodus.bookview;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mSubmitButton;
    private EditText mNameEditText;
    private TextView mMainWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mNameEditText = (EditText) findViewById(R.id.nameEditText);
        mMainWelcome = (TextView) findViewById(R.id.mainWelcome);
        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mMainWelcome.setTypeface(pacificoFont);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}
