package com.epicodus.bookview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.bookview.R;
import com.epicodus.bookview.models.Book;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Shelby Clayton on 12/1/2016.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {
    private ArrayList<Book> mBooks = new ArrayList<>();
    private Context mContext;

    public BookListAdapter(Context context, ArrayList<Book> books) {
        mContext = context;
        mBooks = books;
    }

    @Override
    public BookListAdapter.BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        BookViewHolder viewHolder = new BookViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BookListAdapter.BookViewHolder holder, int position) {
        holder.bindBook(mBooks.get(position));
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.bookImageView) ImageView mBookImageView;
        @Bind(R.id.bookTitleTextView) TextView mBookTitleTextView;
        @Bind(R.id.authorTextView) TextView mAuthorTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindBook(Book book) {
            mBookTitleTextView.setText(book.getTitle());
            mAuthorTextView.setText(book.getAuthors().get(0));
            mRatingTextView.setText("Rating: " + book.getAverageRating());
        }
    }
}
