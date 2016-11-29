package com.epicodus.bookview;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.welcomeUserTextView) TextView mWelcomeUserTextView;
    @Bind(R.id.authorButton) Button mAuthorButton;
    @Bind(R.id.wishlistButton) Button mWishlistButton;
    @Bind(R.id.searchSubmitButton) Button mSearchSubmitButton;
    @Bind(R.id.bookEditText) EditText mBookEditText;

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

        mAuthorButton.setOnClickListener(this);
        mWishlistButton.setOnClickListener(this);
        mSearchSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mAuthorButton) {
            Intent intent = new Intent(MenuActivity.this, AuthorActivity.class);
            startActivity(intent);
        } else if (v == mWishlistButton) {
            Intent intent = new Intent(MenuActivity.this, WishlistActivity.class);
            startActivity(intent);
        } else if (v == mSearchSubmitButton) {
            String query = mBookEditText.getText().toString();
            Intent intent = new Intent(MenuActivity.this, BookResultsActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    }
}
