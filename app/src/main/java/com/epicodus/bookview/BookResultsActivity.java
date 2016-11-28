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

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BookResultsActivity extends AppCompatActivity {
    public static final String TAG = BookResultsActivity.class.getSimpleName();
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
        getBooks(book);
    }

    private void getBooks(String book) {
        final GoodreadsService goodreadsService = new GoodreadsService();
        goodreadsService.searchBooks(book, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
