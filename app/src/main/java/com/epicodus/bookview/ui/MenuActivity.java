package com.epicodus.bookview.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.bookview.Constants;
import com.epicodus.bookview.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mSearchedBookReference;
    private ValueEventListener mSearchedBookReferenceListener;

    @Bind(R.id.welcomeUserTextView) TextView mWelcomeUserTextView;
    @Bind(R.id.authorButton) Button mAuthorButton;
    @Bind(R.id.wishlistButton) Button mWishlistButton;
    @Bind(R.id.searchSubmitButton) Button mSearchSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedBookReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_BOOK_SEARCH);

        mSearchedBookReferenceListener = mSearchedBookReference.addValueEventListener(new ValueEventListener() {
           @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
                   String book = bookSnapshot.getValue().toString();
                   Log.v("location updated", "book " + book);
               }
           }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
            Intent intent = new Intent(MenuActivity.this, BookResultsActivity.class);
            startActivity(intent);
        }
    }
}
