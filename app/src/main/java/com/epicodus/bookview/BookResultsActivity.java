package com.epicodus.bookview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BookResultsActivity extends AppCompatActivity {
    public static final String TAG = BookResultsActivity.class.getSimpleName();
    @Bind(R.id.BookResultsListView) ListView mBookResultsListView;

    public ArrayList<Book> mBooks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        getBooks(query);
    }

    private void getBooks(String query) {
        final GoodreadsService goodreadsService = new GoodreadsService();
        goodreadsService.searchBooks(query, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mBooks = goodreadsService.processResults(response);
                Log.v(TAG, "in on response");

                BookResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String[] bookNames = new String[mBooks.size()];
                        for (int i = 0; i < bookNames.length; i++) {
                            bookNames[i] = mBooks.get(i).getTitle();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(BookResultsActivity.this,
                                android.R.layout.simple_list_item_1, bookNames);
                        mBookResultsListView.setAdapter(adapter);

                        for (Book book : mBooks) {
                            Log.d(TAG, "Title: " + book.getTitle());
                            Log.d(TAG, "Author: " + book.getAuthor());
                            Log.d(TAG, "Image url: " + book.getImageUrl());
                            Log.d(TAG, "Rating: " + Double.toString(book.getRating()));

                        }
                    }
                });
            }
        });
    }
}
