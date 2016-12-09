package com.epicodus.bookview.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.bookview.Constants;
import com.epicodus.bookview.R;
import com.epicodus.bookview.models.Book;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.bookImageView) ImageView mImageLabel;
    @Bind(R.id.bookTitleTextView) TextView mTitleLabel;
    @Bind(R.id.bookAuthorTextView) TextView mAuthorLabel;
    @Bind(R.id.bookDescriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.bookRatingCountTextView) TextView mRatingCountLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.saveBookButton) TextView mSaveBookButton;
    @Bind(R.id.ratingBar) RatingBar mRatingBar;

    private Book mBook;

    public static BookDetailFragment newInstance(Book book) {
        BookDetailFragment bookDetailFragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("book", Parcels.wrap(book));
        bookDetailFragment.setArguments(args);
        return bookDetailFragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        mBook = Parcels.unwrap(getArguments().getParcelable("book"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mBook.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        String rating = Double.toString(mBook.getAverageRating());
        mTitleLabel.setText(mBook.getTitle());
        mAuthorLabel.setText("By " + (android.text.TextUtils.join("\n", mBook.getAuthors())));
        mDescriptionLabel.setText(mBook.getDescription());
        mRatingBar.setRating(Float.parseFloat(String.valueOf(rating)));
        mRatingCountLabel.setText((Integer.toString(mBook.getRatingCount())) + " Ratings");
        Log.v("rating = ", rating);
        Log.v("mRatingBar ", mRatingBar.toString());

        mSaveBookButton.setOnClickListener(this);
        mWebsiteLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mBook.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mSaveBookButton) {
            DatabaseReference bookRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_WISHLIST);
            bookRef.push().setValue(mBook);
            Toast.makeText(getContext(), "Book Saved to Wishlist", Toast.LENGTH_LONG).show();
        }
    }

}
