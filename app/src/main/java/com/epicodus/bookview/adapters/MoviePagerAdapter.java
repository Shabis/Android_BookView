package com.epicodus.bookview.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.bookview.models.Book;
import com.epicodus.bookview.ui.MovieDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 12/2/16.
 */
public class MoviePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Book> mBooks;

    public MoviePagerAdapter(FragmentManager fm, ArrayList<Book> books) {
        super(fm);
        mBooks = books;
    }

    @Override
    public Fragment getItem(int position) {
        return MovieDetailFragment.newInstance(mBooks.get(position));
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
