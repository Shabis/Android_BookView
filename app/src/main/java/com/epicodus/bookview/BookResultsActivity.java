package com.epicodus.bookview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookResultsActivity extends AppCompatActivity {
    @Bind(R.id.BookResultsListView) ListView mBookResultsListView;

    private String[] books = new String[] {"this", "is", "a", "placeholder", "book", "list"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_results);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, books);
        mBookResultsListView.setAdapter(adapter);
        Intent intent = getIntent();
        String book = intent.getStringExtra("book");

    }
}
