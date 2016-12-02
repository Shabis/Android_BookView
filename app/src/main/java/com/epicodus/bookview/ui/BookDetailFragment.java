package com.epicodus.bookview.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.bookview.R;
import com.epicodus.bookview.models.Book;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookDetailFragment extends Fragment {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.bookImageView) ImageView mImageLabel;
    @Bind(R.id.bookTitleTextView) TextView mTitleLabel;
    @Bind(R.id.bookAuthorTextView) TextView mAuthorLabel;
    @Bind(R.id.bookDescriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.bookRatingTextView) TextView mRatingLabel;
    @Bind(R.id.bookRatingCountTextView) TextView mRatingCountLabel;
//    @Bind(R.id.websiteTextView) TextView mWebsiteTextView;
    @Bind(R.id.saveBookButton) TextView mSaveBookButton;

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

        mTitleLabel.setText(mBook.getTitle());
        mAuthorLabel.setText(android.text.TextUtils.join(", ", mBook.getAuthors()));
        mDescriptionLabel.setText(mBook.getDescription());
        mRatingLabel.setText(Double.toString(mBook.getAverageRating()));
        mRatingCountLabel.setText(Integer.toString(mBook.getRatingCount()));

        return view;
    }

}
