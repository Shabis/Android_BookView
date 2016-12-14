package com.epicodus.bookview.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.os.Parcel;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.epicodus.bookview.Constants;
import com.epicodus.bookview.R;
import com.epicodus.bookview.models.Book;
import com.epicodus.bookview.ui.BookDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Shelby Clayton on 12/9/2016.
 */

public class FirebaseBookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseBookViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindBook(Book book) {
        ImageView bookImageView = (ImageView) mView.findViewById(R.id.bookImageView);
        TextView titleTextView = (TextView) mView.findViewById(R.id.bookTitleTextView);
        TextView authorTextView = (TextView) mView.findViewById(R.id.bookAuthorTextView);
        RatingBar ratingBar = (RatingBar) mView.findViewById(R.id.ratingBar);

        Picasso.with(mContext)
                .load(book.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(bookImageView);
        String rating = Double.toString(book.getAverageRating());
        titleTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthors().get(0));
        ratingBar.setRating(Float.parseFloat(String.valueOf(rating)));
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Book> books = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_WISHLIST);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    books.add(snapshot.getValue(Book.class));
                }

                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, BookDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("books", Parcels.wrap(books));
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
