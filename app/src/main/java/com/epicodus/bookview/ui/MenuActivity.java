package com.epicodus.bookview.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.bookview.Constants;
import com.epicodus.bookview.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

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

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

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
            String book = mBookEditText.getText().toString();
            if (!(book).equals("")) {
                addToSharedPreferences(book);
            }
            Intent intent = new Intent(MenuActivity.this, BookResultsActivity.class);
            intent.putExtra("book", book);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String book) {
        mEditor.putString(Constants.SEARCH_PREFERENCE_KEY, book).apply();
    }
}
