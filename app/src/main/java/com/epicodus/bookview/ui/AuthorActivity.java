package com.epicodus.bookview.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.epicodus.bookview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AuthorActivity extends AppCompatActivity {
    @Bind(R.id.authorEditText) EditText mAuthorEditText;
    @Bind(R.id.authorAddButton) Button mAuthorAddButton;
    @Bind(R.id.authorListView) ListView mAuthorListView;
    List<String> authorList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        ButterKnife.bind(this);

        mAuthorAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AuthorActivity.this, "Author Added", Toast.LENGTH_LONG).show();
                String author = mAuthorEditText.getText().toString();
                authorList.add(author);
                ArrayAdapter adapter = new ArrayAdapter(AuthorActivity.this, android.R.layout.simple_list_item_1, authorList);
                mAuthorListView.setAdapter(adapter);
                mAuthorEditText.setText("");
            }
        });
    }
}
