package com.epicodus.bookview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WishlistActivity extends AppCompatActivity {
    @Bind(R.id.bookEditText) EditText mBookEditText;
    @Bind(R.id.bookAddButton) Button mBookAddButton;
    @Bind(R.id.bookListView) ListView mBookListView;
    List<String> bookList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        ButterKnife.bind(this);

        mBookAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WishlistActivity.this, "Book Added", Toast.LENGTH_LONG).show();
                String author = mBookEditText.getText().toString();
                bookList.add(author);
                ArrayAdapter adapter = new ArrayAdapter(WishlistActivity.this, android.R.layout.simple_list_item_1, bookList);
                mBookListView.setAdapter(adapter);
            }
        });
    }
}
