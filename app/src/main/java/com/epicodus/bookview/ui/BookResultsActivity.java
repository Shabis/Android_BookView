package com.epicodus.bookview.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.epicodus.bookview.R;
import com.epicodus.bookview.models.Book;
import com.epicodus.bookview.services.GoogleBooksService;

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
//
//    private String[] books = new String[] {"this", "is", "a", "placeholder", "book", "list"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_results);
        ButterKnife.bind(this);

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, books);
//        mBookResultsListView.setAdapter(adapter);
        Intent intent = getIntent();
        String book = intent.getStringExtra("book");
        getBooks(book);
    }


    private void getBooks(String book){
        final GoogleBooksService googleBooksService = new GoogleBooksService();
        googleBooksService.findBooks(book, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mBooks = googleBooksService.processResults(response);
                BookResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] bookNames = new String[mBooks.size()];
                        for (int i = 0; i < bookNames.length; i++) {
                            bookNames[i] = mBooks.get(i).getTitle();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(BookResultsActivity.this, android.R.layout.simple_list_item_1, bookNames);
                        mBookResultsListView.setAdapter(adapter);

                        for(Book book : mBooks) {
                            Log.d(TAG, "Title: " + book.getTitle());
                            Log.d(TAG, "Title: " + book.getAuthors());
                            Log.d(TAG, "Title: " + book.getDescription());
                            Log.d(TAG, "Title: " + book.getImageUrl());
                            Log.d(TAG, "Title: " + book.getAverageRating());
                            Log.d(TAG, "Title: " + book.getRatingCount());
                        }
                    }
                });
            }
        });
    }
}
