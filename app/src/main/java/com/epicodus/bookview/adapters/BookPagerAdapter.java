package com.epicodus.bookview.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.bookview.models.Book;
import com.epicodus.bookview.ui.BookDetailFragment;

import java.util.ArrayList;

public class BookPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Book> mBooks;

    public BookPagerAdapter(FragmentManager fm, ArrayList<Book> books) {
        super(fm);
        mBooks = books;
    }

    @Override
    public Fragment getItem(int position) {
        return BookDetailFragment.newInstance(mBooks.get(position));
    }

    @Override
    public int getCount() {
        return mBooks.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBooks.get(position).getTitle();
    }
}
