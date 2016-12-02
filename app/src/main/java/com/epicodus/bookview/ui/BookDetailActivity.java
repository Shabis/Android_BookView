package com.epicodus.bookview.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.bookview.R;
import com.epicodus.bookview.adapters.BookPagerAdapter;
import com.epicodus.bookview.models.Book;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private BookPagerAdapter adapterViewPager;
    ArrayList<Book> mBooks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        mBooks = Parcels.unwrap(getIntent().getParcelableExtra("books"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new BookPagerAdapter(getSupportFragmentManager(), mBooks);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
