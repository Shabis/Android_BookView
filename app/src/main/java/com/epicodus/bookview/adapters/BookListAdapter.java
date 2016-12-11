package com.epicodus.bookview.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.epicodus.bookview.Constants;
import com.epicodus.bookview.R;
import com.epicodus.bookview.models.Book;
import com.epicodus.bookview.ui.BookDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Shelby Clayton on 12/1/2016.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

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

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.bookImageView) ImageView mBookImageView;
        @Bind(R.id.bookTitleTextView) TextView mBookTitleTextView;
        @Bind(R.id.bookAuthorTextView) TextView mBookAuthorTextView;
        @Bind(R.id.ratingBar) RatingBar mRatingBar;

        private Context mContext;

        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            itemView.setOnClickListener(this);
        }

        public void bindBook(Book book) {
            String rating = Double.toString(book.getAverageRating());
            Picasso.with(mContext)
                    .load(book.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mBookImageView);
            mBookTitleTextView.setText(book.getTitle());
            mBookAuthorTextView.setText(book.getAuthors().get(0));
            mRatingBar.setRating(Float.parseFloat(String.valueOf(rating)));
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, BookDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("books", Parcels.wrap(mBooks));
            mContext.startActivity(intent);
        }
    }
}
